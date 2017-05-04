package zone01;

import lejos.robotics.Color;

public class Graph {
	private static final Node nodeAUp = new Node();
	private static final Node nodeADown = new Node();
	private static final Node nodeBUp = new Node();
	private static final Node nodeBDown = new Node();
	private static final Node nodeCUp = new Node();
	private static final Node nodeCDown = new Node();
	private static final Node nodeDDown = new Node();
	private static final Node nodeEDown = new Node();
	private static final Node nodeFUp = new Node();
	private static final Node nodeFDown = new Node();
	private static final Node nodeGUp = new Node();
	private static final Node nodeGDown = new Node();
	private static final Node nodeHUp = new Node();
	private static final Node nodeHDown = new Node();
	private static final Node nodeIUp = new Node();
	private static final Node nodeIDown = new Node();
	private static final Node nodeJUp = new Node();
	private static final Node nodeJDown = new Node();
	private static final Node nodeKUp = new Node();
	private static final Node nodeKDown = new Node();
	private static final Node nodeLUp = new Node();
	private static final Node nodeLDown = new Node();
	private static final Node nodeMUp = new Node();
	private static final Node nodeMDown = new Node();
	private static final Node nodeNUp = new Node();
	private static final Node nodeNDown = new Node();
	private static final Node nodeOUp = new Node();
	private static final Node nodeODown = new Node();
	private static final Node nodePUp = new Node();
	private static final Node nodePDown = new Node();

	static {
		nodeAUp.addConnection(new Connection(nodeADown) {
			@Override
			protected ActionBase getOperation() {
				return new ActionFollowToIntersection();
			}
		});

		nodeADown.addConnection(new Connection(nodeAUp) {

			@Override
			protected ActionBase getOperation() {
				return new ActionFollowToIntersection(GlobalConstants.LEFT,
						Color.GREEN);
			}
		});
		nodeADown.addConnection(new Connection(nodeBDown) {
			@Override
			protected ActionBase getOperation() {
				return new ActionRotate(-90, ActionRotate.RIGHT);
			}
		});

		nodeADown.addConnection(new Connection(nodeNUp) {
			@Override
			protected ActionBase getOperation() {
				return new ActionRotate(90, ActionRotate.LEFT);

			}
		});

		nodeBDown.addConnection(new Connection(nodeADown) {
			@Override
			protected ActionBase getOperation() {
				return new ActionRotate(90, ActionRotate.LEFT);
			}
		});

		nodeBDown.addConnection(new Connection(nodeNUp) {
			@Override
			protected ActionBase getOperation() {
				return new ActionCrossLine(GlobalConstants.BOTH);
			}
		});

		nodeBDown.addConnection(new Connection(nodeBUp) {

			@Override
			protected ActionBase getOperation() {
				return new ActionFollowToIntersection(GlobalConstants.LEFT);
			}
		});

		nodeBUp.addConnection(new Connection(nodeBDown) {

			@Override
			protected ActionBase getOperation() {
				return new ActionFollowToIntersection();
			}
		});

		nodeBUp.addConnection(new Connection(nodeCDown) {

			@Override
			protected ActionBase getOperation() {
				return new ActionRotate(-45, ActionRotate.RIGHT);
			}
		});

		nodeBUp.addConnection(new Connection(nodeFDown) {

			@Override
			protected ActionBase getOperation() {
				return new ActionCrossLine(GlobalConstants.BOTH);
			}
		});

		nodeCDown.addConnection(new Connection(nodeBUp) {

			@Override
			protected ActionBase getOperation() {
				return new ActionRotate(45, ActionRotate.LEFT);
			}
		});

		nodeCDown.addConnection(new Connection(nodeCUp) {
			@Override
			protected ActionBase getOperation() {
				return new ActionFollowToIntersection();
			}
		});

		nodeCDown.addConnection(new Connection(nodeFDown) {

			@Override
			protected ActionBase getOperation() {
				return new ActionRotate(135, ActionRotate.RIGHT);
			}
		});

		nodeCUp.addConnection(new Connection(nodeCDown) {

			@Override
			protected ActionBase getOperation() {
				return new ActionFollowToIntersection(GlobalConstants.RIGHT);
			}
		});

		nodeCUp.addConnection(new Connection(nodeEDown) {

			@Override
			protected ActionBase getOperation() {
				return new ActionRotate(45, ActionRotate.LEFT);
			}
		});

		nodeCUp.addConnection(new Connection(nodeDDown) {

			@Override
			protected ActionBase getOperation() {
				return new ActionRotate(-45, ActionRotate.RIGHT);
			}
		});
		nodeDDown.addConnection(new Connection(nodeCUp) {

			@Override
			protected ActionBase getOperation() {
				return new ActionRotate(45, ActionRotate.LEFT);
			}
		});
		nodeDDown.addConnection(new Connection(nodeEDown) {

			@Override
			protected ActionBase getOperation() {
				return new ActionRotate(-90, ActionRotate.RIGHT);
			}
		});

		nodeEDown.addConnection(new Connection(nodeCUp) {

			@Override
			protected ActionBase getOperation() {
				return new ActionRotate(-45, ActionRotate.RIGHT);
			}
		});

		nodeEDown.addConnection(new Connection(nodeDDown) {

			@Override
			protected ActionBase getOperation() {
				return new ActionRotate(90, ActionRotate.LEFT);
			}
		});
	}
}
