/***************************************************************

 Created by william.russell@viasat.com  on 12/6/18

 1. Create a searchable method that can find all files by name within the dir directories and return
 the file paths as well as file names.

 For example:
 private List<String> findFiles(String fileName);  input of text.txt
 prints:
    1. directory.dir1.subdir2.text.txt
    2. directory.dir2.subdir2.subdir2.text.txt

 2. Create a searchable method that finds files by extension type.

 For example:
 private List<String> findFiles(String extension);  input of .png
 prints:
    1. directory.dir1.subdir1.image2.png
    2. directory.dir2.subdir1.subdir1.subdir1.image.png


 3. Create test-ng test cases within test/java/directory

 ***************************************************************/
package directory;