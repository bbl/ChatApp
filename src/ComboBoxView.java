import java.util.Observable;
import java.util.Observer;

import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class ComboBoxView extends JComboBox<Object> implements Observer {

	private ComboBoxModel model;

	public ComboBoxView(ComboBoxModel model) {
		super(model.getAllUsers().toArray());
		this.model = model;
	}

	@Override
	public void update(Observable o, Object arg1) {
		assert o == model;
		System.out.println("TEST");
		if (model.getSize() == 0) {
			this.removeAllItems();
			//System.out.println("TEST1");
		} else {
			//System.out.println("TEST2");
			this.removeAllItems();
			for (int i =0; i<model.getSize(); i++){
				ComboBoxModel.User user = model.getUser(i);
				String online = user.isOnline() ? "Online" : "Offline";
				this.addItem(new String(user.getNick() + " " + online+" "+user.getIp()));
			}
			//int last = model.getSize() - 1;
			//ComboBoxModel.User user = model.getUser(last);
			//String online = user.isOnline() ? "Online" : "Offline";
			//this.addItem(new String(user.getNick() + " " + online+" "+user.getIp()));
		}

	}

	public ComboBoxModel getModelCombo() {
		return model;
	}

	public void setModelCombo(ComboBoxModel model) {
		this.model = model;
	}

}
