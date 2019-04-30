package featurecat.lizzie.gui;

import featurecat.lizzie.Lizzie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;

@SuppressWarnings("serial")
public class AnalysisFrame extends JPanel {
    //private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("featurecat.lizzie.i10n.GuiBundle");

    private JTable analysisTable;
    private AnalysisTableModel analysisTableModel;
	Timer timer;


    public AnalysisTableModel getAnalysisTableModel() {
        return analysisTableModel;
    }

    public JTable getAnalysisTable() {
        return analysisTable;
    }

    public AnalysisFrame() {
        super(new BorderLayout());

        analysisTableModel = new AnalysisTableModel();
        analysisTable = new JTable(analysisTableModel);
        analysisTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        analysisTable.setFillsViewportHeight(true);

        analysisTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        analysisTable.getColumnModel().getColumn(1).setPreferredWidth(50);
        analysisTable.getColumnModel().getColumn(2).setPreferredWidth(65);
        analysisTable.getColumnModel().getColumn(3).setPreferredWidth(50);
        analysisTable.getColumnModel().getColumn(4).setPreferredWidth(650);
        
        timer=new Timer(1000,new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				analysisTable.validate();
				analysisTable.updateUI();
			}
		});
		timer.start();


        // Create the scroll pane and add the table to it.
        // Official programming recommend, or the table head will not show
        JScrollPane scrollPane = new JScrollPane(analysisTable);
        add(scrollPane);

    //    analysisTableModel.setHostTable(analysisTable);
        analysisTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = analysisTable.rowAtPoint(e.getPoint());
                int col = analysisTable.columnAtPoint(e.getPoint());
                if (row >= 0 && col >= 0) {
                    try {
                        handleTableClick(row, col);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    private void handleTableClick(int row, int col) {
//        if ((Lizzie.board.getData().isBlackToPlay() && Lizzie.optionSetting.isShowBlackSuggestion()
//                || !Lizzie.board.getData().isBlackToPlay() && Lizzie.optionSetting.isShowWhiteSuggestion())) {
//            if (row == analysisTableModel.getSelectedMoveIndex()) {
//                analysisTable.clearSelection();
//                analysisTableModel.setSelectedMove(null);
//            } else {
//                analysisTableModel.setSelectedMoveByIndex(row);
//            }
//
//            Lizzie.frame.repaint();
//        } else {
//            analysisTable.clearSelection();
//        }
    }

    /**
     * Create the GUI and show it. For thread safety, this method should be
     * invoked from the event-dispatching thread.
     */
    public static JDialog createAnalysisDialog(JFrame owner) {
        // Create and set up the window.
        JDialog dialog = new JDialog(owner, "测试");

        // Create and set up the content pane.
        final AnalysisFrame newContentPane = new AnalysisFrame();
        newContentPane.setOpaque(true); // content panes must be opaque
        dialog.setContentPane(newContentPane);

        // Display the window.
        dialog.setSize(800, 600);

        // Handle close event
        dialog.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent e) {
         //       newContentPane.getAnalysisTableModel().setSelectedMove(null);
//                Lizzie.optionSetting.setAnalysisWindowShow(false);
            }
        });

        return dialog;
    }
}
