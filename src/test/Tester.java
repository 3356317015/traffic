package test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Tester {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Tester window = new Tester();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(800, 500);
		shell.setText("SWT Application");
		shell.setLayout(new FormLayout());
		
		Button button = new Button(shell, SWT.FLAT | SWT.TOGGLE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				System.out.println("aaaaa");
			}
		});
		FormData fd_button = new FormData();
		fd_button.bottom = new FormAttachment(0, 108);
		fd_button.right = new FormAttachment(0, 161);
		fd_button.top = new FormAttachment(0);
		fd_button.left = new FormAttachment(0);
		button.setLayoutData(fd_button);
		button.setText("New Button(&B)");
		
		

	}
}
