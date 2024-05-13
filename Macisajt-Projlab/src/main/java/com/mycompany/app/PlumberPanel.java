package com.mycompany.app;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;

/**
 * PlumberPanel osztály, amely a szerelők akcióit kezeli
 */
public class PlumberPanel extends JPanel {

    /**
     * Az aktuális szerelő
     */
    private final Plumber plumber;

    /**
     * "Kör vége" gomb
     */
    private final JButton bEndRound = new JButton();

    /**
     * "Cső felvétele" gomb
     */
    private final JButton bGrabPipe = new JButton();

    /**
     * "Cső lerakása" gomb
     */
    private final JButton bPlacePipe = new JButton();

    /**
     * "Pumpa átirányítása" gomb
     */
    private final JButton bRedirect = new JButton();

    /**
     * "Pumpa lerkása" gomb
     */
    private final JButton bPlacePump = new JButton();

    /**
     * "Cső lyukasztása" gomb
     */
    private final JButton bLeak = new JButton();

    /**
     * "Cső ragadóssá tétele" gomb
     */
    private final JButton bSticky = new JButton();

    /**
     * "Mozgás" gomb
     */
    private final JButton bMove = new JButton();

    /**
     * "Javítás" gomb
     */
    private final JButton bRepair = new JButton();

    public PlumberPanel(Plumber plumber) {
        this.plumber = plumber;
        initComponents();
    }

    /**
     * Inicializálja a PlumberPanel komponenseit
     */
    private void initComponents() {
        JLabel lName = new JLabel();
        JLabel lTeam = new JLabel();

        setBackground(View.PRIMARY_COLOR);
        setForeground(View.SECONDARY_COLOR);
        setPreferredSize(new Dimension(300, 500));

        lName.setFont(new Font("Segoe UI", Font.BOLD, 30));
        lName.setForeground(View.SECONDARY_COLOR);
        lName.setHorizontalAlignment(SwingConstants.CENTER);
        lName.setText(plumber.name);

        lTeam.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        lTeam.setForeground(View.SECONDARY_COLOR);
        lTeam.setHorizontalAlignment(SwingConstants.CENTER);
        lTeam.setText("SZERELŐ");

        bEndRound.setBackground(View.SECONDARY_COLOR);
        bEndRound.setFont(new Font("Segoe UI", Font.BOLD, 18));
        bEndRound.setForeground(View.PRIMARY_COLOR);
        bEndRound.setText("KÖR VÉGE");
        bEndRound.addActionListener(this::bEndRoundActionPerformed);

        bGrabPipe.setBackground(View.SECONDARY_COLOR);
        bGrabPipe.setFont(new Font("Segoe UI", Font.BOLD, 14));
        bGrabPipe.setForeground(View.PRIMARY_COLOR);
        bGrabPipe.setText("Cső felvétele");
        bGrabPipe.addActionListener(this::bGrabPipeActionPerformed);

        bPlacePipe.setBackground(View.SECONDARY_COLOR);
        bPlacePipe.setFont(new Font("Segoe UI", Font.BOLD, 14));
        bPlacePipe.setForeground(View.PRIMARY_COLOR);
        bPlacePipe.setText("Cső lerakása");
        bPlacePipe.addActionListener(this::bPlacePipeActionPerformed);

        bRedirect.setBackground(View.SECONDARY_COLOR);
        bRedirect.setFont(new Font("Segoe UI", Font.BOLD, 14));
        bRedirect.setForeground(View.PRIMARY_COLOR);
        bRedirect.setText("<html><p style=\"text-align:center\">Pumpa<br>átirányítása</p></html>");
        bRedirect.addActionListener(this::bRedirectActionPerformed);

        bPlacePump.setBackground(View.SECONDARY_COLOR);
        bPlacePump.setFont(new Font("Segoe UI", Font.BOLD, 14));
        bPlacePump.setForeground(View.PRIMARY_COLOR);
        bPlacePump.setText("<html><p style=\"text-align:center\">Pumpa<br>lerakása</p></html>");
        bPlacePump.addActionListener(this::pPlacePumpActionPerformed);

        bLeak.setBackground(View.SECONDARY_COLOR);
        bLeak.setFont(new Font("Segoe UI", Font.BOLD, 14));
        bLeak.setForeground(View.PRIMARY_COLOR);
        bLeak.setText("Cső lyukasztása");
        bLeak.addActionListener(this::bLeakActionPerformed);

        bSticky.setBackground(View.SECONDARY_COLOR);
        bSticky.setFont(new Font("Segoe UI", Font.BOLD, 14));
        bSticky.setForeground(View.PRIMARY_COLOR);
        bSticky.setText("<html><p style=\"text-align:center\">Cső ragadóssá<br>tétele</p></html>");
        bSticky.addActionListener(this::bStickyActionPerformed);

        bMove.setBackground(View.SECONDARY_COLOR);
        bMove.setFont(new Font("Segoe UI", Font.BOLD, 14));
        bMove.setForeground(View.PRIMARY_COLOR);
        bMove.setText("Mozgás");
        bMove.addActionListener(this::bMoveActionPerformed);

        bRepair.setBackground(View.SECONDARY_COLOR);
        bRepair.setFont(new Font("Segoe UI", Font.BOLD, 14));
        bRepair.setForeground(View.PRIMARY_COLOR);
        bRepair.setText("Javítás");
        bRepair.addActionListener(this::bRepairActionPerformed);

        // Generated by NetBeans
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(bEndRound, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lTeam, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lName, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(bMove, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bRepair, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(bLeak, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(bSticky, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(bGrabPipe, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(bPlacePipe, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(bRedirect, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(bPlacePump, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(lName)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lTeam)
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(bMove, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bRepair, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(bLeak, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bSticky, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(bGrabPipe, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bPlacePipe, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(bRedirect, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bPlacePump, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                                .addComponent(bEndRound, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
        );
        // End of generated code

        refresh();
    }

    /**
     * A "Kör vége" gomb megnyomását kezeli
     *
     * @param evt a gomb megnyomásakor kiváltódott esemény
     */
    private void bEndRoundActionPerformed(ActionEvent evt) {
        Game.nextPlayer();
        Game.getActivePlayer().drawNameAndButtons(View.GAME_WINDOW);
        View.refresh();
    }

    /**
     * A "Pumpa átirányítása" gomb megnyomását kezeli
     *
     * @param evt a gomb megnyomásakor kiváltódott esemény
     */
    private void bRedirectActionPerformed(ActionEvent evt) {
        Object lock = new Object();
        var clickThread = new Thread(() -> {
            Pipe source = null, destination = null;
            var selectorPanel = new SelectorPanel("<html><p style=\"text-align:center\">Válassza<br>ki a bementet!</p></html>", lock);
            View.GAME_WINDOW.setPlayerPanel(selectorPanel);
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException ignored) {
                }
            }
            View.GAME_WINDOW.setPlayerPanel(this);
            if (selectorPanel.selectedComponent != null && selectorPanel.selectedComponent instanceof Pipe pipe)
                source = pipe;
            if (source == null)
                return;
            selectorPanel = new SelectorPanel("<html><p style=\"text-align:center\">Válassza<br>ki a kimenetet!</p></html>", lock);
            View.GAME_WINDOW.setPlayerPanel(selectorPanel);
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException ignored) {
                }
            }
            View.GAME_WINDOW.setPlayerPanel(this);
            if (selectorPanel.selectedComponent != null && selectorPanel.selectedComponent instanceof Pipe pipe)
                destination = pipe;
            if (destination == null)
                return;
            plumber.redirect(source, destination);
            refresh();
            View.refresh();
        });
        clickThread.start();
    }

    /**
     * A "Cső lyukasztása" gomb megnyomását kezeli
     *
     * @param evt a gomb megnyomásakor kiváltódott esemény
     */
    private void bLeakActionPerformed(ActionEvent evt) {
        plumber.leak();
        refresh();
        View.refresh();
    }

    /**
     * A "Cső ragadóssá tétele" gomb megnyomását kezeli
     *
     * @param evt a gomb megnyomásakor kiváltódott esemény
     */
    private void bStickyActionPerformed(ActionEvent evt) {
        plumber.makeItSticky();
        refresh();
        View.refresh();
    }

    /**
     * A "Mozgás" gomb megnyomását kezeli
     *
     * @param evt a gomb megnyomásakor kiváltódott esemény
     */
    private void bMoveActionPerformed(ActionEvent evt) {
        Object lock = new Object();
        var clickThread = new Thread(() -> {
            var selectorPanel = new SelectorPanel(lock);
            View.GAME_WINDOW.setPlayerPanel(selectorPanel);
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException ignored) {
                }
            }
            View.GAME_WINDOW.setPlayerPanel(this);
            if (selectorPanel.selectedComponent != null)
                plumber.move(selectorPanel.selectedComponent);
            refresh();
            View.refresh();
        });
        clickThread.start();
    }

    /**
     * A "Cső felvétele" gomb megnyomását kezeli
     *
     * @param evt a gomb megnyomásakor kiváltódott esemény
     */
    private void bGrabPipeActionPerformed(ActionEvent evt) {
        Object lock = new Object();
        var clickThread = new Thread(() -> {
            var selectorPanel = new SelectorPanel(lock);
            View.GAME_WINDOW.setPlayerPanel(selectorPanel);
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            View.GAME_WINDOW.setPlayerPanel(this);
            if (selectorPanel.selectedComponent != null && selectorPanel.selectedComponent instanceof Pipe pipe)
                plumber.grabPipe(pipe);
            refresh();
            View.refresh();
        });
        clickThread.start();
    }

    /**
     * A "Cső lerakása" gomb megnyomását kezeli
     *
     * @param evt a gomb megnyomásakor kiváltódott esemény
     */
    private void bPlacePipeActionPerformed(ActionEvent evt) {
        plumber.placePipe();
        refresh();
        View.refresh();
    }

    /**
     * A "Pumpa lerakása" gomb megnyomását kezeli
     *
     * @param evt a gomb megnyomásakor kiváltódott esemény
     */
    private void pPlacePumpActionPerformed(ActionEvent evt) {
        plumber.placePump();
        refresh();
        View.refresh();
    }

    /**
     * A "Javítás" gomb megnyomását kezeli
     *
     * @param evt a gomb megnyomásakor kiváltódott esemény
     */
    private void bRepairActionPerformed(ActionEvent evt) {
        plumber.repair();
        refresh();
        View.refresh();
    }


    /**
     * A gombok állapotának frissítése
     */
    public void refresh() {
        bGrabPipe.setEnabled(!plumber.actionPerformed && plumber.component != null && (plumber.component instanceof Pump || plumber.component instanceof Cistern) && plumber.grabbedPipe == null);
        bPlacePipe.setEnabled(!plumber.actionPerformed && plumber.component != null && plumber.component instanceof Pump && plumber.grabbedPipe != null);
        bRedirect.setEnabled(!plumber.actionPerformed && plumber.component != null && plumber.component instanceof Pump && plumber.grabbedPipe == null);
        bPlacePump.setEnabled(!plumber.actionPerformed && plumber.component != null && plumber.component instanceof Pipe && plumber.grabbedPipe == null && plumber.grabbedPump != null);
        bLeak.setEnabled(!plumber.actionPerformed && plumber.component != null && plumber.component instanceof Pipe pipe && pipe.isLeakable() && plumber.grabbedPipe == null);
        bSticky.setEnabled(!plumber.actionPerformed && plumber.component != null && plumber.component instanceof Pipe pipe && pipe.notSticky() && plumber.grabbedPipe == null);
        bMove.setEnabled(!plumber.moved && plumber.ableToMove);
        bRepair.setEnabled(!plumber.actionPerformed && plumber.component != null && ((plumber.component instanceof Pipe pipe && pipe.isBroken()) || (plumber.component instanceof Pump pump && pump.isBroken())) && plumber.grabbedPipe == null);
    }
}
