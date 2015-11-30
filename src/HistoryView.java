import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextArea;

public class HistoryView extends JTextArea implements Observer {
	private HistoryModel model;
	private int messageCount = 0;

	public HistoryView() {
		super();
	}

	public HistoryView(HistoryModel model) {
		super();
		this.model = model;
	}

	public HistoryModel getModel() {
		return model;
	}

	public void setModel(HistoryModel model) {
		this.model = model;
	}

	@Override
	public void update(Observable o, Object arg) {
		assert o == model;
		if (model.getSize() == 0) {
			this.setText("");
			refresh();
		} else {
			int lastPos = model.getSize() - 1;
			HistoryModel.Message m = model.getMessage(lastPos);
			this.setText(m.getDate() + " " + m.getNick() + " " + m.getText());
		}

	}

	private void refresh() {
		//
	}

}
