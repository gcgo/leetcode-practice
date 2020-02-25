package bytedance;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * leetcode Hard588
 * 设计一个内存文件系统，模拟以下功能：
 * <p>
 * ls： 以字符串的格式输入一个路径。如果它是一个文件的路径，那么函数返回一个列表，仅包含这个文件的名字。
 * 如果它是一个文件夹的的路径，那么返回该 文件夹内 的所有文件和子文件夹的名字。你的返回结果（包括文件和子文件夹）应该按字典序排列。
 * <p>
 * mkdir：输入一个当前不存在的 文件夹路径 ，你需要根据路径名创建一个新的文件夹。
 * 如果有上层文件夹路径不存在，那么你也应该将它们全部创建。这个函数的返回类型为 void 。
 * <p>
 * addContentToFile： 输入字符串形式的 文件路径 和 文件内容 。
 * 如果文件不存在，你需要创建包含给定文件内容的文件。如果文件已经存在，那么你需要将给定的文件内容 追加 在原本内容的后面。
 * 这个函数的返回类型为 void。
 * <p>
 * readContentFromFile： 输入 文件路径 ，以字符串形式返回该文件的 内容 。
 */
public class FileSystem {
    class Dir {
        HashMap<String, Dir> dirs = new HashMap<>();//存的是<文件夹名称，文件夹对象>
        HashMap<String, String> files = new HashMap<>();//存的是<文件名，内容>
    }

    Dir root;

    public FileSystem() {
        root = new Dir();
    }

    public List<String> ls(String path) {
        Dir t = root;//从根开始
        List<String> files = new ArrayList<>();//新建一个文件列表
        if (!path.equals("/")) {//如果不是根目录
            String[] d = path.split("/");
            for (int i = 1; i < d.length - 1; i++) {//从1开始，到倒数第二个
                t = t.dirs.get(d[i]);//一层一层的找文件夹,找到最后一层。
            }
            if (t.files.containsKey(d[d.length - 1])) {//在最后一层文件夹下找有没有指定文件
                files.add(d[d.length - 1]);//有的话添加到文件列表中，并返回列表
                return files;
            } else {//没有的话，证明不是文件，而是文件夹，则在文件夹里找。
                t = t.dirs.get(d[d.length - 1]);
            }
        }
        files.addAll(new ArrayList<>(t.dirs.keySet()));//文件夹名
        files.addAll(new ArrayList<>(t.files.keySet()));//文件名
        Collections.sort(files);
        return files;
    }

    public void mkdir(String path) {
		Dir t = root;
		String[] d = path.split("/");
		for (int i = 1; i < d.length; i++) {
			if (!t.dirs.containsKey(d[i]))
				t.dirs.put(d[i], new Dir());
			t = t.dirs.get(d[i]);
		}
    }

    public void addContentToFile(String filePath, String content) {
		Dir t = root;
		String[] d = filePath.split("/");
		for (int i = 1; i < d.length - 1; i++) {
			t = t.dirs.get(d[i]);//t先来到要添加文件内容的文件夹下。
		}
		//getOrDefault如果有该文件就返回其内容，没有则返回空字符串，无论哪种，最后都会加上content
		t.files.put(d[d.length - 1], t.files.getOrDefault(d[d.length - 1], "") + content);
    }

    public String readContentFromFile(String filePath) {
		Dir t = root;
		String[] d = filePath.split("/");
		for (int i = 1; i < d.length - 1; i++) {//i=0为空字符串，i=length-1为文件名
			t = t.dirs.get(d[i]);
		}
		return t.files.get(d[d.length - 1]);
    }

    @Test
    public void test1() {
    	FileSystem fileSystem =new FileSystem();
    	fileSystem.mkdir("/a1/b1/c1/d1");
    	fileSystem.mkdir("/a1/b2/c2");
    	fileSystem.mkdir("/a3/b3/c3");

		System.out.println(fileSystem.ls("/a1/b1/c1").toString());

		fileSystem.addContentToFile("/a1/b1/c1/haha.txt","测试文件系统");

		System.out.println(fileSystem.ls("/a1/b1/c1").toString());

		System.out.println(fileSystem.readContentFromFile("/a1/b1/c1/haha.txt"));

	}
}
