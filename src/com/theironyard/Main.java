package com.theironyard;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    //    refactor
    public static ArrayList<Post> parsePost(String fileName) throws FileNotFoundException {

        ArrayList<Post> posts = new ArrayList<>();
        // parse file
        File f = new File(fileName);
        Scanner fileScanner = new Scanner(f);
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            String[] columns = line.split("\\|");
            Post post = new Post(Integer.valueOf(columns[0]), columns[1], columns[2]);
            posts.add(post);
        }
        return posts;
    }

    public static void printPosts(ArrayList<Post> posts, int currentPost) {
        int postId = 0;
        for (Post post : posts) {
            if (post.replyId == currentPost) {
                System.out.printf("[%s] %s by %s\n", postId, post.text, post.author);
            }
            postId++;
        }

    }
// end of refactor

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Post> posts = parsePost("posts.txt");

//      Start loop
        Scanner consoleScanner = new Scanner(System.in);
        int currentPost = -1;
//
// print out the replies to currentPost
        while (true) {
            printPosts(posts, currentPost);

//            ask for new id
            System.out.println("Type the id you want to see replies to:");
            currentPost = Integer.valueOf(consoleScanner.nextLine());

        }
    }
}
