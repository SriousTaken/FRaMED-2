package org.framed.iorm.ui.pattern.connections.roleconstraint;

import org.eclipse.graphiti.features.context.IAddConnectionContext;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.styles.LineStyle;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.framed.iorm.model.Relation;
import org.framed.iorm.model.Type;
import org.framed.iorm.ui.literals.IdentifierLiterals;
import org.framed.iorm.ui.literals.NameLiterals;
import org.framed.iorm.ui.util.PropertyUtil;

/**
 * This graphiti pattern is used to work with {@link Relation}s
 * of the type {@link Type#ROLE_IMPLICATION} in the editor.
 * <p>
 * It deals with the following aspects of role implications:<br>
 * (1) creating role implications, especially their business object<br>
 * (2) adding role implications to the diagram, especially their pictogram elements<br>
 * <p>
 * It is a subclass of {@link AbstractRoleConstraintPattern} and several operations used here are implemented there.
 * @author Kevin Kassin
 */
public class RoleImplicationPattern extends AbstractRoleConstraintPattern {
	
	/**
	 * the name of the feature gathered from {@link NameLiterals}
	 */
	private static final String ROLEIMPLICATION_FEATURE_NAME = NameLiterals.ROLEIMPLICATION_FEATURE_NAME;
	
	/**
	 * the identifier for the icon of the create feature gathered from {@link IdentifierLiterals}
	 */
	private static final String IMG_ID_FEATURE_ROLEIMPLICATION = IdentifierLiterals.IMG_ID_FEATURE_ROLEIMPLICATION;
	
	/**
	 * Class constructor
	 */
	public RoleImplicationPattern() {
		super();
	}
	
	/**
	 * get method for the features name
	 * @return the name of the feature
	 */
	@Override
	public String getCreateName() {
		return ROLEIMPLICATION_FEATURE_NAME;
	}
	
	/**
	 * get method for the identifier of the icon for the create feature
	 * @return the id of the icon
	 */
	@Override
	public String getCreateImageId() {
		return IMG_ID_FEATURE_ROLEIMPLICATION;
	}
	
	
	//add feature
	//~~~~~~~~~~~
	/**
	 * uses the operation {@link AbstractRoleConstraintPattern#canAddRoleConstraint} of the super type to calculate if
	 * the role implication can be added
	 */
	@Override
	public boolean canAdd(IAddContext addContext) {
		return canAddRoleConstraint(addContext, Type.ROLE_IMPLICATION);
	}
	
	/**
	 * adds the role implication to the pictogram diagram using the following steps:
	 * <p>
	 * Step 1: create a connection shape and dashed polyline as its graphic algorithm
	 * Step 2: create the a connection decorator and a arrowhead as its graphic algorithm 
	 * Step 3: link the pictogram elements and the business objects
	 */
	@Override
	public PictogramElement add(IAddContext addContext) {
		IAddConnectionContext addConnectionContext = (IAddConnectionContext) addContext;
	    Relation addedRoleImplication = (Relation) addContext.getNewObject();
	    Anchor sourceAnchor = addConnectionContext.getSourceAnchor();
	    Anchor targetAnchor = addConnectionContext.getTargetAnchor();
	    //Step 1
	    Connection connection = pictogramElementCreateService.createFreeFormConnection(getDiagram());
	    connection.setStart(sourceAnchor);
	    connection.setEnd(targetAnchor);
	    Polyline polyline = graphicAlgorithmService.createPolyline(connection);
	    polyline.setForeground(manageColor(COLOR_CONSTRAINT_CONNECTION));
	    polyline.setLineStyle(LineStyle.DASH);
	    polyline.setLineWidth(2);
	    //Step2
	    ConnectionDecorator connectionDecorator;
	    connectionDecorator = pictogramElementCreateService.createConnectionDecorator(connection, false, 1.0, true);
	    int points[] = new int[] { -1*ARROWHEAD_LENGTH, ARROWHEAD_HEIGHT, 		//Point 1
	    						   0, 0, 										//P2
	    						   -1*ARROWHEAD_LENGTH, -1*ARROWHEAD_HEIGHT };	//P3						 
	    Polygon arrowhead = graphicAlgorithmService.createPolygon(connectionDecorator, points);
	    arrowhead.setForeground(manageColor(COLOR_CONSTRAINT_CONNECTION));
	    arrowhead.setBackground(manageColor(COLOR_ARROWHEAD));
	    PropertyUtil.setShape_IdValue(connectionDecorator, SHAPE_ID_ROLE_CONSTRAINT_DECORATOR);
	    //Step 3
	    link(connection, addedRoleImplication);
	    return connection;
	}
	 
	//create feature
	//~~~~~~~~~~~~~~  
	/**
	 * uses the operation {@link AbstractRoleConstraintPattern#createRoleConstraint} of the super type to create the role implication
	 */
	@Override
	public Connection create(ICreateConnectionContext createContext) {
		return super.createRoleConstraint(createContext, Type.ROLE_IMPLICATION, this);
	}
}
