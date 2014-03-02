package eu.ha3.matmos.editor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import eu.ha3.matmos.editor.interfaces.EditorModel;

/*
--filenotes-placeholder
*/

@SuppressWarnings("serial")
public class JsonPanel extends JPanel
{
	private EditorModel model;
	
	private JTextArea textArea;
	
	public JsonPanel(EditorModel modelConstruct)
	{
		this.model = modelConstruct;
		
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		
		this.textArea = new JTextArea();
		this.textArea.setEditable(false);
		this.textArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
		this.textArea.setLineWrap(true);
		scrollPane.setViewportView(this.textArea);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnGeneratePretty = new JButton("Generate Pretty");
		btnGeneratePretty.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				JsonPanel.this.textArea.setText(JsonPanel.this.model.generateJson(true));
			}
		});
		panel.add(btnGeneratePretty);
		
		JButton btnGenerateMinified = new JButton("Generate Minified");
		btnGenerateMinified.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				JsonPanel.this.textArea.setText(JsonPanel.this.model.generateJson(false));
			}
		});
		panel.add(btnGenerateMinified);
		
		JButton btnCopyToClipboard = new JButton("Copy to Clipboard");
		btnCopyToClipboard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					StringSelection selection = new StringSelection(JsonPanel.this.textArea.getText());
					Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, selection);
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		panel.add(btnCopyToClipboard);
	}
}
