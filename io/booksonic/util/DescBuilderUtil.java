package io.booksonic.util;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class DescBuilderUtil {
	public ArrayList<File> nfoFiles = new ArrayList();
	private File audiobookDirectory = null;

	public DescBuilderUtil(String directory) {
		this.audiobookDirectory = new File(directory);
	}

	public void setDirectory(String directory) {
		this.audiobookDirectory = new File(directory);
	}

	private void getAllAudiobookDirectories(File currentFile) {
		File[] listOfFiles = currentFile.listFiles();

		for (int i = 0; i < listOfFiles.length; ++i) {
			if (listOfFiles[i].isDirectory()) {
				this.getAllAudiobookDirectories(listOfFiles[i]);
			} else if (listOfFiles[i].getName().toLowerCase().endsWith(".nfo")) {
				this.nfoFiles.add(listOfFiles[i]);
			}
		}

	}

	public ArrayList<Audiobook> getAllAudiobooks() {
		ArrayList<Audiobook> books = new ArrayList<Audiobook>();
		this.getAllAudiobookDirectories(this.audiobookDirectory);

		Iterator var2 = this.nfoFiles.iterator();

		while (var2.hasNext()) {
			File tempFile = (File) var2.next();
			Audiobook theBook = new Audiobook();
			theBook.setTheFile(tempFile);
			books.add(theBook);
			this.createDescFile(theBook);
		}

		return books;
	}

	private void createDescFile(Audiobook theBook) {
		try {
			File descFile = new File(theBook.getTheFile().getParentFile() + "/desc.txt");
			if (!descFile.exists()) {
				BufferedWriter bw = new BufferedWriter(
						new FileWriter(new File(theBook.getTheFile().getParentFile() + "/desc.txt")));
				boolean startWriting = false;
				// We don't have a desc.txt file but we know we have an info
				// file
				try (BufferedReader br = new BufferedReader(new FileReader(theBook.getTheFile()))) {
					String line;
					while ((line = br.readLine()) != null) {
						if (startWriting) {
							bw.write(line);
							bw.newLine();
						}
						if (line.contains("escription")) {
							startWriting = true;
						}
					}
				}
				bw.close();
			}
		} catch (Exception var6) {
			System.out.println("fuck");
			System.out.println(theBook.getTheFile().getName());
			var6.printStackTrace();
		}

	}
}
