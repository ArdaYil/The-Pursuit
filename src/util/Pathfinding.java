package util;

import main.Game;
import vector.Vector2D;

public class Pathfinding {
    private Game panel;
    private Node start;
    private Node goal;
    private LinkedList<Node> closed = new LinkedList<>();
    private LinkedList<Node> open = new LinkedList<>();

    public Pathfinding(Game panel, Vector2D start, Vector2D goal) {
        this.panel = panel;

        this.start = new Node(new Vector2D(start.getX() / this.panel.tileSize, start.getY() / this.panel.tileSize), null);
        this.goal = new Node(goal, null);

        this.start.gCost = 0;
        this.goal.hCost = 0;
        this.goal.fCost = 0;
    }

    public Path computePath() {
        Node currentNode = this.start;

        while (true) {
            Node[] surroundingNodes = this.getSurroundingNodes(currentNode);

            this.setFCostToNodes(surroundingNodes, currentNode);
            this.addToOpen(surroundingNodes);
            this.closed.addLast(currentNode);

            int index = this.getIndexOfNodeInOpen(currentNode);
            if (index >= 0) this.open.remove(index);

            Node parent = currentNode;

            if (this.open.isEmpty()) return null;

            currentNode = this.getNewNode();

            if (currentNode == null) break;

            if (currentNode.position.equals(this.goal.position)) {
                this.goal.parent = parent;
                break;
            }
        }

        return this.createPath();
    }

    private Path createPath() {
        Node currentNode = this.goal;

        LinkedList<Vector2D> path = new LinkedList<>();

        while (currentNode != null) {
            path.addLast(new Vector2D(currentNode.position.getX() * this.panel.tileSize, currentNode.position.getY() * this.panel.tileSize));

            Node[] surroundingNodes = this.getSurroundingNodes(currentNode);

            for (Node surroundingNode : surroundingNodes) {
                if (surroundingNode == null) continue;

                Vector2D startPos = this.start.position;

                if (startPos.equals(surroundingNode.position)) return new Path(path);
            }

            currentNode = currentNode.parent;
        }

        return new Path(path);
    }

    public int getIndexOfNodeInOpen(Node node) {
        int counter = 0;

        for (Node openNode : this.open) {
            if (openNode.position.equals(node.position)) return counter;
            counter++;
        }

        return -1;
    }

    private void setFCostToNodes(Node[] nodes, Node root) {
        for (Node node : nodes) {
            if (node == null) continue;
            if (this.isClosed(node)) continue;

            node.setFCost(root);
        }
    }

    private void addToOpen(Node[] surroundingNodes) {
        for (int i = 0; i < surroundingNodes.length; i++) {
            Node surroundingNode = surroundingNodes[i];

            if (surroundingNode == null) continue;
            if (this.isClosed(surroundingNode)) continue;
            if (this.panel.map.isObstacle(surroundingNode.position)) continue;

            int j = -1;

            for (Node openNode : this.open) {
                j++;

                if (!openNode.position.equals(surroundingNode.position)) continue;

                this.open.remove(j);
                break;
            }

            this.open.addLast(surroundingNode);
        }
    }

    private boolean isClosed(Node node) {
        for (Node checkNode : this.closed) {
            if (node.position.equals(checkNode.position)) return true;
        }

        return false;
    }

    private Node getNewNode() {
        LinkedList<Node> currentNodes = new LinkedList<>();

        Node first = this.open.getFirst();
        int lowest = first.fCost;
        currentNodes.addLast(first);

        for (Node node : this.open) {
            if (node.position.equals(first.position)) continue;

            if (node.fCost == lowest) {
                currentNodes.addLast(node);
            }

            else if (node.fCost < lowest) {
                currentNodes = new LinkedList<>();
                currentNodes.addLast(node);
                lowest = node.fCost;
            }
        }

        if (currentNodes.size() == 0) return null;

        int lowestHCost = currentNodes.getFirst().hCost;
        Node currentNode = currentNodes.getFirst();

        for (Node node : currentNodes) {
            if (node.hCost < lowestHCost) {
                currentNode = node;
                lowestHCost = node.hCost;
            }
        }

        return currentNode;
    }

    private Node[] getSurroundingNodes(Node node) {
        Node[] array = new Node[4];
        int counter = 0;

        array[counter++] = new Node(new Vector2D(node.getX(), node.getY() - 1), node);
        array[counter++] = new Node(new Vector2D(node.getX() - 1, node.getY()), node);
        array[counter++] = new Node(new Vector2D(node.getX() + 1, node.getY()), node);
        array[counter] = new Node(new Vector2D(node.getX(), node.getY() + 1), node);

        int count = 0;

        for (Node surroundingNode : array) {
            int x = surroundingNode.getX();
            int y = surroundingNode.getY();

            Vector2D baseDimension = this.panel.map.getBaseDimensions();

            if (x < 0 || x >= baseDimension.getX()) array[count] = null;
            else if (y < 0 || y >= baseDimension.getY()) array[count] = null;

            count++;
        }

        return array;
    }

    public int getFCostFromOpen(Vector2D position) {
        for (Node node : this.open) {
            if (node.position.equals(position)) return node.hCost;
        }

        return -1;
    }

    public int getFCostFromClosed(Vector2D position) {
        for (Node node : this.closed) {
            if (node.position.equals(position)) return node.hCost;
        }

        return -1;
    }

    private class Node {
        private Vector2D position;
        private int gCost;
        private int hCost;
        private int fCost;
        private Node parent;

        public Node(Vector2D position, Node parent) {
            this.position = position;
            this.parent = parent;
        }

        public int getX() {
            return this.position.getX();
        }

        public int getY() {
            return this.position.getY();
        }

        private int getGCost(Node root) {
            int x = Math.abs(this.getX() - root.getX());
            int y = Math.abs(this.getY() - root.getY());

            return x + y + root.gCost;
        }

        private int getHCost() {
            int x = Math.abs(goal.getX() - this.getX());
            int y = Math.abs(goal.getY() - this.getY());

            return x + y;
        }

        private void setFCost(Node root) {
            int gCost = this.getGCost(root);
            int hCost = this.getHCost();

            this.gCost = gCost;
            this.hCost = hCost;
            this.fCost = gCost + hCost;
        }

        @Override
        public String toString() {
            return "Node: { gCost: " +
                    this.gCost + ", hCost: " +
                    this.hCost + ", fCost: " +
                    this.fCost + ", " +
                    this.position;
        }
    }

    public class Path {
        public LinkedList<Vector2D> pathList;

        public Path(LinkedList pathList) {
            this.pathList = pathList;
        }

        public Vector2D getNext() {
            return this.pathList.deleteLast();
        }

        public boolean hasNext() {
            return !this.pathList.isEmpty();
        }

        @Override
        public String toString() {
            return this.pathList.toString();
        }
    }
}
