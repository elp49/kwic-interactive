package interactive;

import linestorage.LineStorageProcessor;

public class InteractiveWindowDriver implements Runnable {

	private boolean is_started_;

	private LineStorageModel storageModel = null;
	private LineInputWindow inputWindow = null;
	private LineStorageDisplayWindow storageDisplayWindow = null;
	private LineStorageController storageController = null;

	public InteractiveWindowDriver(LineStorageProcessor storage) {
		storageModel = new LineStorageModel(storage);
		inputWindow = new LineInputWindow(storage);
		storageDisplayWindow = new LineStorageDisplayWindow();
		storageController = new LineStorageController();
		is_started_ = false;
	}

	@Override
	public synchronized void run() {
		storageDisplayWindow.setModel(storageModel);
		storageController.setModel(storageModel);
		storageController.setInputWindow(inputWindow);

		while (true) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void start() {
		if (!is_started_) {
			is_started_ = true;
			Thread thread = new Thread(this);
			thread.start();
		}
	}

	public void stop() {
		is_started_ = false;
	}

}
