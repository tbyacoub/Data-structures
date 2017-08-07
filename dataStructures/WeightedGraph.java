package dataStructures;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import interfaces.EdgeMeasure;
import interfaces.Graph;


public class WeightedGraph<S, T> implements Graph<S, T>{

	private HashMap<String, Vertex> verticies;

	private boolean directedGraph;

	public WeightedGraph() {
		this(false);
	}

	public WeightedGraph(boolean directed) {
		verticies = new HashMap<>();
		directedGraph = directed;
	}

	private class Vertex {
		private S vData;
		private Set<Edge> edges;

		private Vertex(S value) {
			edges = new HashSet<>();
			vData = value;
		}

		private void addEdge(Edge e) {
			if (!edges.contains(e)) {
				edges.add(e);
			}
		}

	}

	private class Edge {
		String startPoint;
		String endPoint;
		T eData;

		private Edge(String from, String to, T value) {
			this.startPoint = from;
			this.endPoint = to;
			eData = value;
		}
	}

	@Override
	public boolean isDirected() {
		return directedGraph;
	}

	@Override
	public void addVertex(String vertexLabel, S vertexData) {
		if (verticies.containsKey(vertexLabel)) {
			throw new IllegalStateException("No duplicates in verticies is allowed!");
		}
		verticies.put(vertexLabel, new Vertex(vertexData));
	}

	@Override
	public void removeVertex(String vertexLabel) {
		if (!verticies.containsKey(vertexLabel))
			throw new IllegalStateException("Graph does not contain " + vertexLabel + " as one of its verticies.");
		verticies.remove(vertexLabel);
	}

	@Override
	public void addEdge(String sourceLabel, String targetLabel, T edgeData) {
		if (!verticies.containsKey(sourceLabel) || !verticies.containsKey(targetLabel)) {
			throw new IllegalStateException("One, or both of the verticies do not exist!");
		}
		Edge newEdge = new Edge(sourceLabel, targetLabel, edgeData);
		verticies.get(sourceLabel).addEdge(newEdge);
		if (!directedGraph) {
			newEdge = new Edge(targetLabel, sourceLabel, edgeData);
			verticies.get(targetLabel).addEdge(newEdge);
		}
	}

	@Override
	public T getEdgeData(String sourceLabel, String targetLabel) {
		if (!verticies.containsKey(sourceLabel) || !verticies.containsKey(targetLabel)) {
			throw new IllegalStateException("One, or both of the verticies do not exist!");
		}
		Vertex srcVertex = verticies.get(sourceLabel);
		for (Edge edge : srcVertex.edges) {
			if (edge.endPoint.equals(targetLabel)) {
				return edge.eData;
			}
		}
		return null;
	}

	@Override
	public S getVertexData(String label) {
		if (!verticies.containsKey(label))
			throw new IllegalStateException("Vertex: " + label + " is not found!");
		return verticies.get(label).vData;
	}

	@Override
	public int getNumVertices() {
		return verticies.size();
	}

	@Override
	public int getNumEdges() {
		int edgeSum = 0;
		for (String v : getVertices()) {
			edgeSum += getNeighbors(v).size();
		}
		return (isDirected()) ? edgeSum : edgeSum / 2;
	}

	@Override
	public Collection<String> getVertices() {
		return verticies.keySet();
	}

	@Override
	public Collection<String> getNeighbors(String label) {
		if (!verticies.containsKey(label))
			throw new IllegalStateException("Vertex: " + label + " is not found!");
		ArrayList<String> neighbors = new ArrayList<>();
		for (Edge e : verticies.get(label).edges) {
			neighbors.add(e.endPoint);
		}
		return neighbors;
	}

	@Override
	public List<String> topologicalSort() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<String> shortestPath(String startLabel, String destLabel, EdgeMeasure<T> measure) {
		if (!verticies.containsKey(startLabel) || !verticies.containsKey(destLabel)) {
			throw new IllegalStateException("One, or both of the verticies do not exist!");
		}
		Map<String, String> predecessors = new HashMap<>();
		Map<String, Double> weight = new HashMap<>();
		Set<String> closed = new HashSet<>();
		Set<String> open = new HashSet<>();
		initSPCollections(weight, predecessors, open, startLabel);
		while (!open.isEmpty()) {
			String minVertex = getMinVertex(open, weight);
			open.remove(minVertex);
			closed.add(minVertex);
			updateNeighbors(minVertex, closed, open, weight, predecessors, measure);
		}

		return getShortestPathList(startLabel, destLabel, predecessors);
	}

	private void initSPCollections(Map<String, Double> weight, Map<String, String> predecessors, Set<String> open,
			String startLabel) {
		for (String vertex : getVertices()) {
			weight.put(vertex, Double.MAX_VALUE);
			predecessors.put(vertex, "");
		}
		weight.put(startLabel, 0.0);
		open.add(startLabel);
	}

	private void updateNeighbors(String minVertex, Set<String> closed, Set<String> open, Map<String, Double> weight,
			Map<String, String> predecessors, EdgeMeasure<T> measure) {
		for (String neighbor : getNeighbors(minVertex)) {
			if (!closed.contains(neighbor)) {
				Double newWeight = weight.get(minVertex) + measure.getCost(getEdgeData(minVertex, neighbor));
				if (newWeight < weight.get(neighbor)) {
					open.add(neighbor);
					weight.replace(neighbor, newWeight);
					predecessors.replace(neighbor, minVertex);
				}

			}
		}
	}

	private List<String> getShortestPathList(String startLabel, String destLabel, Map<String, String> predecessors) {
		List<String> sp = new ArrayList<>();
		String trg = destLabel;
		sp.add(0, trg);
		while (!trg.equals(startLabel)) {
			trg = predecessors.get(trg);
			sp.add(0, trg);
		}
		return sp;
	}

	private String getMinVertex(Collection<String> open, Map<String, Double> weight) {
		Iterator<String> openIter = open.iterator();
		String minVertex = null;
		Double minValue = Double.MAX_VALUE;
		while (openIter.hasNext()) {
			String temp = openIter.next();
			if (weight.get(temp) < minValue) {
				minVertex = temp;
				minValue = weight.get(temp);
			}
		}
		return minVertex;
	}

	@Override
	public Graph<S, T> minimumSpanningTree(EdgeMeasure<T> measure) {
		WeightedGraph<S, T> tree = new WeightedGraph<>();;
		EdgeComparator ec = new EdgeComparator(measure);
		PriorityQueue<Edge> edgeQueue = new PriorityQueue<>(ec);
		HashSet<String> visited = new HashSet<>();
		String src = getVertices().iterator().next();
		tree.addVertex(src, getVertexData(src));
		while (tree.getNumVertices() < getNumVertices()) {
			visited.add(src);
			for (Edge edge : verticies.get(src).edges) {
				if (!visited.contains(edge.endPoint)) {
					edgeQueue.add(edge);
				}
			}
			Edge minEdge = edgeQueue.poll();
			while (visited.contains(minEdge.endPoint)) {
				minEdge = edgeQueue.poll();
			}
			src = minEdge.startPoint;
			String trg = minEdge.endPoint;
			tree.addVertex(trg, getVertexData(trg));
			tree.addEdge(src, trg, minEdge.eData);
			src = trg;
		}
		return tree;
	}

	@Override
	public double getTotalCost(EdgeMeasure<T> measure) {
		double tCost = 0;
		for (String curVertex : getVertices()) {
			for (String neighbor : getNeighbors(curVertex)) {
				tCost += measure.getCost(getEdgeData(curVertex, neighbor));
			}
		}
		return (isDirected()) ? tCost : tCost / 2;
	}

	private class EdgeComparator implements Comparator<Edge> {

		private EdgeMeasure<T> measure;

		public EdgeComparator(EdgeMeasure<T> measure) {
			this.measure = measure;
		}

		@Override
		public int compare(WeightedGraph<S, T>.Edge o1, WeightedGraph<S, T>.Edge o2) {
			return Double.compare(measure.getCost(o1.eData), measure.getCost(o2.eData));
		}

	}

	public String toString() {
		StringBuilder retVal = new StringBuilder();
		StringBuilder temp = new StringBuilder();
		for (String vertex : getVertices()) {
			Vertex curV = verticies.get(vertex);
			temp.append("\n*Vertex: " + vertex + ", Vertex Data: " + curV.vData + "\n");
			temp.append("\t-Edges: \n");
			boolean empty = true;
			for (Edge e : curV.edges) {
				empty = false;
				temp.append("\t\t" + "+to: " + e.endPoint + ", data: " + e.eData + "\n");
			}
			if (!empty) {
				retVal.append(temp.toString());
			}
			temp.setLength(0);
		}
		return retVal.toString();
	}

	public Set<String> BFS() {
		Set<String> bfs = new LinkedHashSet<>();
		Set<String> visited = new HashSet<>();
		String start = getVertices().iterator().next();
		Queue<String> q = new ArrayDeque<>();
		q.add(start);
		visited.add(start);
		while (!q.isEmpty()) {
			String poped = q.poll();
			bfs.add(poped);
			for (String neighbor : getNeighbors(poped)) {
				if (!visited.contains(neighbor)) {
					q.add(neighbor);
					visited.add(neighbor);
				}
			}
		}
		return bfs;
	}

	public Set<String> DFS() {
		Set<String> dfs = new LinkedHashSet<>();
		Set<String> visited = new HashSet<>();
		String start = getVertices().iterator().next();
		Stack<String> s = new Stack<>();
		s.add(start);
		visited.add(start);
		while (!s.isEmpty()) {
			String poped = s.pop();
			dfs.add(poped);
			for (String neighbor : getNeighbors(poped)) {
				if (!visited.contains(neighbor)) {
					s.push(neighbor);
					visited.add(neighbor);
				}
			}
		}
		return dfs;
	}

}
