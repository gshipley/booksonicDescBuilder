DescBuilder is a small utility I wrote in Java to help manage my audiobooks before I import them into booksonic.  I find that I already have a .nfo file for most of my books but booksonic looks for a file called desc.txt to display the book information.

This little utility will scan your audiobooks for a .nfo file that contains a book description.  If it finds a description, it will create a desc.txt file with the description in the directory where it found the nfo file.

Usage: java -jar DescBuilder /path/to/audiobooks

I have tested this on a collection of 800 books with good results.
