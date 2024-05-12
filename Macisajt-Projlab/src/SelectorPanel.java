import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * SelectorPanel osztály, amely a felhasználó által a pályán egérkattintással történő komponensválasztást kezeli
 */
public class SelectorPanel extends JPanel implements MouseListener {

    /**
     * A kiválasztott komponens
     */
    public Component selectedComponent = null;
    private final String message;
    private final Object lock;

    public SelectorPanel(String message, Object lock) {
        this.message = message;
        this.lock = lock;
        initComponents();
    }

    public SelectorPanel(Object lock) {
        this("<html><p style=\"text-align:center\">Kattintson<br>egy elemre!</p></html>", lock);
    }

    /**
     * Inicializálja a SelectorPanel komponenseit
     */
    private void initComponents() {
        JButton bCancel = new JButton();
        JLabel lMessage = new JLabel();

        setBackground(View.PRIMARY_COLOR);
        setForeground(View.SECONDARY_COLOR);
        setPreferredSize(new Dimension(300, 500));

        bCancel.setBackground(View.SECONDARY_COLOR);
        bCancel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        bCancel.setForeground(View.PRIMARY_COLOR);
        bCancel.setText("MÉGSE");
        bCancel.addActionListener(this::bCancelActionPerformed);

        lMessage.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lMessage.setForeground(View.SECONDARY_COLOR);
        lMessage.setHorizontalAlignment(SwingConstants.CENTER);
        lMessage.setText(message);

        // Generated by NetBeans
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addComponent(bCancel, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(79, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lMessage, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(102, Short.MAX_VALUE)
                                .addComponent(lMessage, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bCancel, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                .addGap(116, 116, 116))
        );
        // End of generated code

        GameWindow.map.addMouseListener(this);
    }

    private void finish() {
        synchronized (lock) {
            lock.notify();
        }
        GameWindow.map.removeMouseListener(this);
    }

    /**
     * A "Mégse" gomb megnyomását kezeli
     *
     * @param evt a gomb megnyomásakor kiváltódott esemény
     */
    private void bCancelActionPerformed(ActionEvent evt) {
        finish();
    }

    /**
     * Az egérrel való kattintást kezeli
     *
     * @param e egérrel való kattintás következtében kiváltódott esemény
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        synchronized (lock) {
            if (!SwingUtilities.isLeftMouseButton(e))
                return;

            ArrayList<Component> selections = new ArrayList<>();
            Component selectedNode = null;
            Component selectedNeighborPipe = null;

            for (int i = Game.Instance.pipelineSystem.components.size() - 1; i >= 0; i--)
                if (Game.Instance.pipelineSystem.components.get(i).intersect(e.getPoint()))
                    selections.add(Game.Instance.pipelineSystem.components.get(i));

            if (selections.size() == 0)
                return;

            for (var selection : selections) {
                if (selection instanceof Node)
                    selectedNode = selection;
                if (selection instanceof Pipe pipe && Game.getActivePlayer().component instanceof Node node && pipe.isNeighborWith(node))
                    selectedNeighborPipe = selection;
            }

            if (selectedNode != null)
                selectedComponent = selectedNode;
            else if (selectedNeighborPipe != null)
                selectedComponent = selectedNeighborPipe;
            else
                selectedComponent = selections.get(0);
            finish();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}