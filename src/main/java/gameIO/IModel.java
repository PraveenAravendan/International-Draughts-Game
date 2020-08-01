package main.java.gameIO;

import main.java.model.board.IBoardModel;
import main.java.model.modelToView.ICommand;
import main.java.model.viewToModel.IModelManager;


/**
 * Combines the running and management interfaces of the model.
 */
public interface IModel extends IModelManager{

	/**
	 * Sets the management interface of the view used by the model.
	 * @param viewManager
	 * @param turnManager
	 */
	
	void setCommand(final ICommand p0);
    
	IBoardModel getBoardModel();

}
