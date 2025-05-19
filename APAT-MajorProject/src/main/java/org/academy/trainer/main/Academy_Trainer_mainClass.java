package org.academy.trainer.main;

import java.io.IOException;

import javax.swing.SwingUtilities;

import org.academy.trainer.webUI.Wvf_Trainer_UI;

public class Academy_Trainer_mainClass {

	public static void main(String[] args) throws IOException {
		SwingUtilities.invokeLater(() -> new Wvf_Trainer_UI());
	}
}

	