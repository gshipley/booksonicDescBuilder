package io.booksonic.util;

import java.io.File;
import java.util.ArrayList;

public class DescBuilder {

	public static ArrayList<File> nfoFiles = new ArrayList();
	
	public static void main(String[] args) {
		 if(args.length == 0) {
	            System.err.println("Usage java -jar DescBuilder /path/to/audiobooks");
	            System.exit(1);
	     }
		
		String audioBookLocation = args[0];
		DescBuilderUtil util = new DescBuilderUtil(audioBookLocation);
		ArrayList<Audiobook> results = util.getAllAudiobooks();
		for(int i=0; i<results.size(); i++) {
			System.out.println("Processed " + results.get(i).getTheFile().toString());
		}
	}
}
