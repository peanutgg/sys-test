package test.practice.list;

public class MyList<T> {
    private int size = 0;
    private Node<T> head;
    private Node<T> tail;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Node<T> getHead() {
        return head;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }

    public Node<T> getTail() {
        return tail;
    }

    public void setTail(Node<T> tail) {
        this.tail = tail;
    }

    public static class Node<T> {


        private Node<T> prev;
        private T data;
        private Node<T> next;


        public Node<T> getPrev() {
            return prev;
        }

        public void setPrev(Node<T> prev) {
            this.prev = prev;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }


        public Node(Node<T> prev, T data, Node<T> next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }

        public Node() {
        }

    }

    public T get(int index) {
        int mid = size >> 1;
        Node<T> midNode = getMidNode();
        Node<T> result = midNode;
        if (index > mid) {
            for (; mid < size; mid++) {
                if (mid == index) {
                    return result.data;
                }
                result = result.next;
            }
        } else {
            for (; mid > 0; mid--) {
                if (mid == index) {
                    return result.data;
                }
                result = result.prev;
            }
        }

        /*if (index < (size >> 1)) {
            Node<T> e = head;
            for (int i = 0; i < index; i++) {
                e = e.next;
                return e.data;
            }
        }else{
            Node<T> e = tail;
            for (int i = size - 1; i > index; i--) {
                e = e.prev;
                return e.data;
            }
        }*/




        return result.data;
    }

    public void add(T data) {
        if (head == null) {
            head = new Node(null, data, tail);
            tail = new Node(head, null, null);
            head.next = tail;
            size++;
        } else {
            if (size == 1) {
                tail.data = data;
                size++;
            } else {
                Node<T> element = new Node(tail, data, null);
                tail.next = element;
                tail = element;
                size++;
            }
        }
    }

    public boolean remove(int index) {
        int mid = size >>> 1;
        Node<T> midNode = getMidNode();
        Node<T> result = midNode;
        if (index > mid) {
            for (; mid < index; mid++) {
                if (mid == index) {
                    unlink(result);
                    return true;
                }
                result = result.next;
            }
        }else{
            for (; mid >= index; mid--) {
                if (mid == index) {
                    unlink(result);
                    return true;
                }
                result = result.prev;
            }
        }
        return false;
    }

    public boolean remove(T data) {
        int mid = size >>> 1;
        Node<T> midNode = getMidNode();
        Node<T> result = null;
        if (data == null) {
            for (Node<T> e = head; e.next != null; e = e.next) {
                if (e.data == null) {
                    unlink(e);
                    return true;
                }
            }
        } else {
            for (Node<T> e = head; e.next != null; e = e.next) {
                if (e.equals(midNode)) {
                    unlink(e);
                    return true;
                }
            }
        }
        return false;
    }


    public Node<T> getMidNode() {
        Node<T> midNode = null;
        int mid = size >> 1;
        Node<T> c = head;
        int i = 0;

        while (c != null && c.next != null) {
            if (mid == i) {
                midNode = c;
            }
            c = c.next;
            i++;
        }
        return midNode;
    }


    public void unlink(Node<T> e) {
        Node<T> prev = e.prev;
        Node<T> next = e.next;
        if (prev == null) {
            head = next;
        }else{
            prev.next = next;
            e.prev = null;
        }
        if (next == null) {
            tail = prev;
        }else{
            e.next = null;
        }
        e.data = null;

        size--;
    }
}
