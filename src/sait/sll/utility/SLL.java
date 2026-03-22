package sait.sll.utility;

import java.io.Serializable;

import java.io.File;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

import java.io.FileOutputStream;
import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;

public class SLL implements LinkedListADT, Serializable {

    private Node first;
    private Node last;

    // got help from:
    // https://howtodoinjava.com/java/collections/arraylist/serialize-deserialize-arraylist/
    public void serialization() {
        File file = new File("res/node.ser");

        // turn sll list into arraylist to get data only
        ArrayList<Object> sll_list = new ArrayList<Object>();

        Node current = first;

        // get data only not next (data to add to file)
        while (current != null) {
            sll_list.add(current.getData());
            current = current.getNext();
        }

        // output to write
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(sll_list);
        } catch (IOException e) {
            System.out.println("Error while writing sll data into file " + e.getMessage());
            return;
        }

    }

    // got help from:
    // https://howtodoinjava.com/java/collections/arraylist/serialize-deserialize-arraylist/
    public void deserialization() {
        File file = new File("res/node.ser");
        // reads from file
        ArrayList<Object> sll_list = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            sll_list = (ArrayList) in.readObject(); // returns
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Error while reading sll data in file " + e.getMessage());
            return;
        }

        clear();// originalally I did "SLL list = new SLL();"" but it was causing errors, so AI
                // told me to clear it since we want the original
        for (int i = 0; i < sll_list.size(); i++) {
            append(sll_list.get(i));
        }
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void clear() {
        if (isEmpty())
            return;

        Node iter = first;
        Node temp = first;
        while (iter.getNext() != null) {
            iter = iter.getNext();
            temp.setNext(null);
            temp = iter;
        }
        // removing first and last make it empty
        first = null;
        last = null;
    }

    public void append(Object data) {
        // checks if list is empty
        if (isEmpty()) {
            Node add = new Node(data);
            first = add;
            last = add;
        }

        else {
            //
            Node add = new Node(data);
            // add to end of the linked list
            last.setNext(add);
            add.setNext(null);
            last = add;
        }
    }

    public void prepend(Object data) {
        if (isEmpty()) {
            Node add = new Node(data);
            first = add;
            last = add;
        }

        else {
            Node add = new Node(data);
            // make add the the head
            add.setNext(first);
            first = add;
        }

    }

    public void insert(Object data, int index) throws IndexOutOfBoundsException {
        int length = size();
        Node add = new Node(data);

        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException();
        }

        if (isEmpty() && index == 0) {
            first = add;
            last = add;
        }

        // checks id index is at head, then call prepend
        else if (index == 0) {
            prepend(data);
        }

        // checks if index is tail, then call append
        else if (index == length) {
            append(data);
        }

        // if somewhere in between
        else {
            Node iter = first;
            for (int x = 0; x < length; x++) {

                if (x == index && iter.getNext() != null) {
                    Node temp = first;
                    while (temp.getNext() != iter) {
                        temp = temp.getNext();
                    }
                    temp.setNext(add);
                    add.setNext(iter);
                    break;
                }

                iter = iter.getNext();
            }

        }
    }

    public void replace(Object data, int index) throws IndexOutOfBoundsException {
        int length = size();

        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException();
        }

        else if (index == 0) {
            first.setData(data);
        }

        else if (index == length - 1) {
            last.setData(data);
        }

        else {
            Node iter = first;
            for (int x = 0; x < length; x++) {
                if (x == index && iter != null) {
                    // once index is found set the data to new data
                    iter.setData(data);
                }
                iter = iter.getNext();
            }

        }
    }

    public void delete(int index) throws IndexOutOfBoundsException {
        int length = size();

        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException();
        }

        else if (index == 0 && first == last) {
            Node temp = first.getNext();
            first.setNext(null);
            first = temp;
            last = temp;

        }

        // deletes by skipping over using set next or get next
        else if (index == 0) {// head: deletes first element
            Node temp = first.getNext();
            first.setNext(null);
            first = temp;

        }

        else if (index == length - 1) {// tail: deletes last element
            Node temp = first;
            while (temp.getNext() != last) {
                temp = temp.getNext();
            }
            temp.setNext(null);
            last = temp;
        }

        else {// deletes somewhere in the middle
            Node iter = first;
            Node temp = first;
            for (int x = 0; x < length; x++) {
                if (x == index && iter != null) {

                    while (temp.getNext() != iter) {
                        temp = temp.getNext();
                    }

                    temp.setNext(iter.getNext());
                    iter.setNext(null);
                    break;
                }
                iter = iter.getNext();
            }

        }
    }

    // get size of list
    public int size() {
        int size = 0;
        if (isEmpty())
            return 0;
        else {
            Node temp = first;
            while (temp.getNext() != null) {
                size++;
                temp = temp.getNext();
            }
            return ++size;
        }

    }

    public boolean contains(Object data) {
        if (isEmpty()) {
            return false;
        }

        else {
            Node iter = first;
            while (iter != null) {
                // checks if the list contains a specific data using .equals
                if (iter.getData().equals(data)) {
                    return true;
                }
                iter = iter.getNext();
            }

        }

        return false;

    }

    public int indexOf(Object data) {

        int index = 0;
        if (isEmpty()) {
            return -1;
        }

        Node iter = first;
        while (iter != null) {
            if (iter.getData().equals(data))
                // once the data matches, it will return the index
                return index;
            iter = iter.getNext();
            // gets index of an item in the linked list by incrementing index++
            index++;

        }
        return -1;
    }

    public Object retrieve(int index) throws IndexOutOfBoundsException {
        int length = size();

        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException();
        }

        else if (index == 0) {
            // returns head data if index is head
            return first.getData();
        }

        else if (index == length - 1) {
            // returns tail data if index is tail
            return last.getData();
        }

        else {

            Node iter = first;
            for (int x = 0; x < length; x++) {
                if (x == index && iter != null) {
                    // once index is found then it will return the data
                    return iter.getData();
                }
                iter = iter.getNext();
            }

        }

        return null;

    }

}
