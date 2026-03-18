package sait.sll.utility;

public class SLL implements LinkedListADT {

    private Node first;
    private Node last;


    public boolean isEmpty(){
        return first==null;

    }

    public void clear(){
        if(isEmpty())
            return;
        
        Node iter = first;
        Node temp=first;
        while(iter.getNext()!=null){
            iter=iter.getNext();
            temp.setNext(null);
            temp=iter;
        }
        first=null;
        last=null;
    }

    public void append(Object data){
        if(isEmpty()){
            Node add=new Node(data);
            first=add;
            last=add;
            }

            
        else{
            Node add=new Node(data);
            last.setNext(add);
            add.setNext(null);
            last=add;}
    }

    public void prepend(Object data){
        if(isEmpty()){
            Node add=new Node(data);
            first=add;
            last=add;
            }

            
        else{
            Node add=new Node(data);
            add.setNext(first);
            first=add;}

    }

	public void insert(Object data, int index) throws IndexOutOfBoundsException{
        int length = size();
        Node add = new Node(data);
       
        if(index<0||index>length){
            throw new IndexOutOfBoundsException();
        }
       
        if(isEmpty()&&index==0){
            first=add;
            last=add;
        }

        else if(index==0){
            prepend(data);
        }

        else if(index==length){
            append(data);
        }

        else{
            Node iter=first;
            for(int x=0;x<length;x++){

                if(x==index&&iter.getNext()!=null){
                    Node temp=first;
                    while(temp.getNext()!=iter){
                        temp=temp.getNext();
                    }
                    temp.setNext(add);
                    add.setNext(iter);
                    break;
                }

                iter=iter.getNext();
            }

        }
    }

	public void replace(Object data, int index) throws IndexOutOfBoundsException{
        int length=size();
        
        if(index<0||index>=length){
            throw new IndexOutOfBoundsException();
        }

        else if(index==0){
            first.setData(data);
        }

        else if(index==length-1){
            last.setData(data);
        }

        else{
            Node iter=first;
            for(int x=0;x<length;x++){
                if(x==index&&iter!=null){
                    iter.setData(data);
                }
                iter=iter.getNext();
            }

        }
    }

	public void delete(int index) throws IndexOutOfBoundsException{
        int length=size();
        
        if(index<0||index>=length){
            throw new IndexOutOfBoundsException();
        }

        else if(index==0&&first==last){
            Node temp=first.getNext();
            first.setNext(null);
            first=temp;
            last=temp;

        }

        else if(index==0){
            Node temp=first.getNext();
            first.setNext(null);
            first=temp;

        }

        else if(index==length-1){
            Node temp=first;
            while(temp.getNext()!=last){
                temp=temp.getNext();
            }
            temp.setNext(null);
            last=temp;
        }

        else{
            Node iter=first;
            Node temp=first;
            for(int x=0;x<length;x++){
                if(x==index&&iter!=null){
                    
                    while(temp.getNext()!=iter){
                        temp=temp.getNext();
                    }

                    temp.setNext(iter.getNext());
                    iter.setNext(null);
                    break;
                }
                iter=iter.getNext();
            }

            


        }
    }

    public int size(){
        int size=0;
        if(isEmpty())
            return 0;
        else{
            Node temp=first;
            while(temp.getNext()!=null){
                size++;
                temp=temp.getNext();
            }
            return ++size;
        }

    }

    public boolean contains(Object data){
        if(isEmpty()){
            return false;
        }
    
        else{
            Node iter=first;
            while(iter!=null){
                if(iter.getData().equals(data)){
                    return true;
                }
                iter=iter.getNext();
            }

        }

        return false;
        
    }
    
    public int indexOf(Object data){

        int index=0;
        if(isEmpty()){
            return -1;
        }

        Node iter = first;
        while(iter!=null){
            if(iter.getData().equals(data))
                return index;
            iter=iter.getNext();
            index++;

        }
        return -1;
    }

	public Object retrieve(int index) throws IndexOutOfBoundsException{
        int length=size();
        
        if(index<0||index>=length){
            throw new IndexOutOfBoundsException();
        }

        else if(index==0){
            return first.getData();
        }

        else if(index==length-1){
            return last.getData();
        }

        else{
            Node iter=first;
            for(int x=0;x<length;x++){
                if(x==index&&iter!=null){
                    return iter.getData();
                }
                iter=iter.getNext();
            }

        }


        return null;



    }









}
