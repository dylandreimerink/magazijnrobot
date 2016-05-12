//Authors: Mike Veltman & Dylan Reimerink (ICTM2a)

package bppSimulatorv2;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsConfigTemplate;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;

import javax.media.j3d.Alpha;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.GraphicsConfigTemplate3D;
import javax.media.j3d.Material;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.TransformGroup;
import javax.swing.JPanel;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;

import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class SimulatiePanel extends JPanel {

	int displayWidth, displayHeight;

	public SimulatiePanel() {
		super();

		displayWidth = this.getWidth();
		displayHeight = getHeight();

		GraphicsConfigTemplate3D gc3D = new GraphicsConfigTemplate3D();
		gc3D.setSceneAntialiasing(GraphicsConfigTemplate.PREFERRED);
		GraphicsDevice gd[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();

		Canvas3D canvas = new Canvas3D(gd[0].getBestConfiguration(gc3D), false);

		canvas.setSize(500, 500);

		add(canvas);

		SimpleUniverse univ = new SimpleUniverse(canvas);
		univ.getViewingPlatform().setNominalViewingTransform();

		BranchGroup scene = createSceneGraph();
		scene.compile();
		univ.addBranchGraph(scene);

		setVisible(true);
	}

	private static BranchGroup createSceneGraph() {
		// Make a scene graph branch
		BranchGroup branch = new BranchGroup();

		// Make a changeable 3D transform
		TransformGroup trans = new TransformGroup();
		trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		branch.addChild(trans);

		// Make a shape
		ColorCube demo = new ColorCube(0.4);
		trans.addChild(demo);

		// Make a behavor to spin the shape
		Alpha spinAlpha = new Alpha(-1, 4000);
		RotationInterpolator spinner = new RotationInterpolator(spinAlpha, trans);
		spinner.setSchedulingBounds(new BoundingSphere(new Point3d(), 1000.0));
		trans.addChild(spinner);

		return branch;
	}

	public void addBox(float x, float y, float z, Color3f diffuse, Color3f spec) {
		// Add a box with the given dimension

		// First setup an appearance for the box
		Appearance app = new Appearance();
		Material mat = new Material();
		mat.setDiffuseColor(diffuse);
		mat.setSpecularColor(spec);
		mat.setShininess(5.0f);

		app.setMaterial(mat);
		Box box = Box(x, y, z, app);

		// Create a TransformGroup and make it the parent of the box
		TransformGroup tg = new TransformGroup();
		tg.addChild(box);

		// Then add it to the rootBranchGroup
		rootBranchGroup.addChild(tg);
	}

	public void setBoxList(ArrayList<Box> boxlist) {

	}

	public Dimension getPreferredSize() {
		return new Dimension(300, 650);
	}

	public void paintComponent(Graphics g) {

	}

}