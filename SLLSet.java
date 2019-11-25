package lab5;

public class SLLSet{
    private int size;
    private SLLNode begRef;

    //constructor1
    public SLLSet(){
        size = 0;
        begRef = null;
    }

    //constructor2
    public SLLSet(int[] sortedArray){
        size = sortedArray.length;
        SLLNode temp = null;
        
        for(int i =size-1;i>=0;i--){
            temp = new SLLNode(sortedArray[i], temp);
        }
        
        begRef = temp;
    }

    public int getSize(){
        return size;
    }
    
    public SLLNode getHead(){
        return begRef;
    }

    public SLLSet copy(){
        SLLSet copied = new SLLSet();
        if(begRef!=null){
            copied.size = size;
            copied.begRef = new SLLNode(begRef.value, begRef.next);

            SLLNode temp = copied.begRef;
            SLLNode iter = begRef.next;

            while(temp.next!=null){
                temp.next = new SLLNode(iter.value,iter.next);
                temp = temp.next;
                iter = iter.next;
            }
        }
  
       return copied;
    }

    public boolean isIn(int v){
        SLLNode temp = begRef;
        for(int i = 0; i<size;i++){
            if (temp.value  == v){
                return true;
            }
            temp = temp.next;
        }
        
        return false;
    }

    public void add(int v){
        if(isIn(v)==false){
            SLLNode ref = begRef;
            SLLNode temp;
            if(begRef==null){
                begRef = new SLLNode(v, null);
            }
            else if(begRef.value<v){
                while (ref.next!=null && ref.next.value<v){
                    ref = ref.next;
                }
                temp = ref.next; 
                ref.next= new SLLNode(v,temp);
            }
            else{
                temp = new SLLNode(v, begRef);
                begRef = temp;
            }
            size++;
        }

    }

    public void remove(int v){
        if(isIn(v)==true){
            SLLNode ref = begRef;
            if(begRef.value!=v){
                while (ref.next!=null && ref.next.value!=v){
                    ref = ref.next;
                }
                ref.next = ref.next.next;
            }
            else{
                begRef = begRef.next;
            }
        size--;      
        }
    }

    public SLLSet union(SLLSet s){
        
        SLLSet unionset= new SLLSet();

        if(this.begRef!=null){
            unionset= this.copy();
        }
        
        else if(s.begRef!=null){
            unionset= s.copy();
        }

        
        SLLNode temp = s.begRef;
    
        for(int i=0; i<s.size;i++){
            unionset.add(temp.value);
            temp = temp.next;
        }

    
        return unionset;
    }

    public SLLSet intersection(SLLSet s){
        SLLSet interset = new SLLSet();
        SLLNode temp = s.begRef;
        
        for(int i=0; i<s.size;i++){
            if(this.isIn(temp.value)){
                interset.add(temp.value);
            }
            temp = temp.next;
        }


        return interset;
    }

    public SLLSet difference(SLLSet s){
        SLLSet diffset = this.copy();
        SLLNode temp = s.begRef;

        for(int i=0; i<s.size;i++){
            if(diffset.isIn(temp.value)){
                diffset.remove(temp.value);
            }
            else if (diffset.isIn(temp.value)!=true){
                diffset.add(temp.value);
            }
            temp = temp.next;
        }

        return diffset;
    }

    public static SLLSet union(SLLSet[] sArray){
        SLLSet arrunion = new SLLSet();
        
        for(int i = 0;i<sArray.length;i++){
            arrunion = arrunion.union(sArray[i]);
        }

        return arrunion;
    }

    public String toString(){
        String s = null;
        if(begRef!=null){
        s= new String("");
        SLLNode temp = begRef;

        while(temp.next!=null){
            s = s + temp.value + "," ;
            temp = temp.next;
        }

        s = s + temp.value;

        
    }
    return s;
    }
}