package com.mycompany.projetoestoque;

import view.PrincipalFrame;

public class ProjetoEstoque {

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(() -> {

            new PrincipalFrame()
                    .setVisible(true);

        });
    }
}