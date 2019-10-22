/*Author: Satyajeet Singh, Delhi Technological University*/
    import java.io.*;
    import java.util.*;
    import java.text.*; 
    import java.lang.*;

public class Main implements Runnable{
/*********************************************Constants******************************************/
    static PrintWriter out=new PrintWriter(new OutputStreamWriter(System.out));        
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static long mod=(long)1e9+7;
    static long mod1=998244353;
    static boolean sieve[];
    static ArrayList<Integer> primes;
    static long factorial[],invFactorial[];
    static HashSet<Integer> graph[];
    static boolean oj = System.getProperty("ONLINE_JUDGE") != null;
/****************************************Solutions Begins*****************************************/
    public static void main (String[] args) throws Exception {
        String st[]=br.readLine().split(" ");
        long n=Long.parseLong(st[0]);
        int m=Integer.parseInt(st[1]);
        
/****************************************Solutions Ends**************************************************/
        out.flush();
        out.close();
    }
/****************************************Template Begins************************************************/
/***************************************Precision Printing**********************************************/
    static void printPrecision(double d){
        DecimalFormat ft = new DecimalFormat("0.000000"); 
        out.println(ft.format(d));
    }
/******************************************Graph*********************************************************/
    static void Makegraph(int n){
        graph=new HashSet[n];
        for(int i=0;i<n;i++){
            graph[i]=new HashSet<>();
        }
    }
    static void addEdge(int a,int b){
        graph[a].add(b);
    }    

/*********************************************PAIR********************************************************/
    static class PairComp implements Comparator<Pair>{
        public int compare(Pair p1,Pair p2){
            if(p1.u>p2.u){
                return 1;
            }
            else if(p1.u<p2.u){
                return -1;
            }
            else{
                return p1.v-p2.v;
            }
        }
    }
    static class Pair implements Comparable<Pair> {
        int u;
        int v;
        int index=-1;
        public Pair(int u, int v) {
            this.u = u;
            this.v = v;
        }
 
        public int hashCode() {
            int hu = (int) (u ^ (u >>> 32));
            int hv = (int) (v ^ (v >>> 32));
            return 31 * hu + hv;
        }
 
        public boolean equals(Object o) {
            Pair other = (Pair) o;
            return u == other.u && v == other.v;
        }
 
        public int compareTo(Pair other) {
            if(index!=other.index)
                return Long.compare(index, other.index);
            return Long.compare(v, other.v)!=0?Long.compare(v, other.v):Long.compare(u, other.u);
        }
 
        public String toString() {
            return "[u=" + u + ", v=" + v + "]";
        }
    }
/*******************************************LONG PAIR****************************************************/
    static class PairCompL implements Comparator<Pairl>{
        public int compare(Pairl p1,Pairl p2){
            long aa=p2.v-p1.v;
            if(aa<0){
                return -1;
            }
            else if(aa>0){
                return 1;
            }
            else{
                return 0;
            }
        }
    }
    static class Pairl implements Comparable<Pairl> {
            long u;
            long v;
            int index=-1;
            public Pairl(long u, long v) {
                this.u = u;
                this.v = v;
            }
    
            public int hashCode() {
                int hu = (int) (u ^ (u >>> 32));
                int hv = (int) (v ^ (v >>> 32));
                return 31 * hu + hv;
            }
    
            public boolean equals(Object o) {
                Pairl other = (Pairl) o;
                return u == other.u && v == other.v;
            }
    
            public int compareTo(Pairl other) {
                if(index!=other.index)
                    return Long.compare(index, other.index);
                return Long.compare(v, other.v)!=0?Long.compare(v, other.v):Long.compare(u, other.u);
            }
    
            public String toString() {
                return "[u=" + u + ", v=" + v + "]";
            }
        }
/*****************************************DEBUG***********************************************************/
    public static void debug(Object... o) {
        if(!oj)
        System.out.println(Arrays.deepToString(o));
    }
/*****************************************NUMBER THEORY****************************************************/
/************************************MODULAR EXPONENTIATION***********************************************/
    static long modulo(long a,long b,long c) {
        long x=1;
        long y=a%c;
        while(b > 0){
            if(b%2 == 1){
                x=(x*y)%c;
            }
            y = (y*y)%c; // squaring the base
            b /= 2;
        }
        return  x%c;
    }
/********************************************GCD**********************************************************/
    static long gcd(long x, long y)
    {
        if(x==0)
            return y;
        if(y==0)
            return x;
        long r=0, a, b;
        a = (x > y) ? x : y; // a is greater number
        b = (x < y) ? x : y; // b is smaller number
        r = b;
        while(a % b != 0)
        {
            r = a % b;
            a = b;
            b = r;
        }
        return r;
    }
/******************************************SIEVE**********************************************************/
    static void sieveMake(int n){
        sieve=new boolean[n];
        Arrays.fill(sieve,true);
        sieve[0]=false;
        sieve[1]=false;
        for(int i=2;i*i<n;i++){
            if(sieve[i]){
                for(int j=i*i;j<n;j+=i){
                    sieve[j]=false;
                }
            }
        }
        primes=new ArrayList<Integer>();
        for(int i=0;i<n;i++){
            if(sieve[i]){
                primes.add(i);
            }
        }        
    }
/***************************************FACTORIAL*********************************************************/
    static void fact(int n){
        factorial=new long[n+1];
        invFactorial=new long [n+1];
        factorial[0]=1l;
        invFactorial[0]=1l;
        for(int i=1;i<=n;i++){
            factorial[i]=(factorial[i-1]*i)%mod;
            invFactorial[i]=modulo(factorial[i],mod-2,mod);
        }
    }
/*******************************************ncr*********************************************************/
    static long ncr(int n,int k){
        long aa=invFactorial[n-k];
        long bb=invFactorial[k];
        long cc=factorial[n];
        long ans=(aa*cc)%mod;
        ans=(ans*bb)%mod;
        return ans;
    }
/***************************************STRING REVERSE****************************************************/
    static String reverse(String str){
        char r[]=new char[str.length()];
        int j=0;
        for(int i=str.length()-1;i>=0;i--){
            r[j]=str.charAt(i);
            j++;
        }
        return new String(r);
    }
/*****************************************Matrix Exponentiation**********************************************/
    static long mmod=mod*mod;
    static long[][] matmul(long A[][],long B[][]){
        int n=A.length;
        int p=A[0].length;
        int m=B[0].length;
        long ans[][]=new long[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                for(int k=0;k<p;k++){
                    long aa=(A[i][k]*B[k][j])%mod;
                    ans[i][j]=ans[i][j]+aa;;
                    if(ans[i][j]>=mmod){
                        ans[i][j]-=mmod;
                    }
                    if(ans[i][j]>=mod){
                        ans[i][j]-=mod;
                    }
                }
            }
        }
        return ans;
    }
    static long[][] matexpo(long A[][],long exp){
        if(exp==1){
            return A;
        }        
        long B[][]=matexpo(A,exp/2);
        if(exp%2==0){
            return matmul(B,B);
        }
        else{
            return matmul(matmul(B,B),A);
        }
    }
/************************************************DSU***************************************************/
    static class DSU{
        int dsu[];
        long dsu_size[];
        int unconnected=0;
        long curr=0;
        DSU(int n){
            dsu=new int[n];
            dsu_size=new long[n];
            Arrays.fill(dsu,-1);
            Arrays.fill(dsu_size,0l);
            unconnected=n-1;
            for(int i=1;i<=n-1;i++){
                makeSet(i);
            }
        }
        void makeSet(int v){
            dsu[v]=v;
            dsu_size[v]=1;
        }
        int findSet(int v){
            if(dsu[v]==v){
                return v;
            }
            int v1=findSet(dsu[v]);
            dsu[v]=v1;
            return v1;
        }
        long getSize(int s1){
            int a=findSet(s1);
            return dsu_size[a];
        }
        void union(int s1,int s2){
            int a=findSet(s1);
            int b=findSet(s2);
            if(a!=b){
                unconnected--;
                if(dsu_size[a]>dsu_size[b]){
                    int temp=a;
                    a=b;
                    b=temp;
                }
                dsu[a]=b;
                dsu_size[b]+=dsu_size[a];
            }
        }
    }
/*******************************************Bit Manipulation**************************************************/
    static int set(int mask,int bit){
        return (mask|(1<<bit));
    }
    static int unset(int mask,int bit){
        return (mask&(~(1<<bit)));
    }
    static int countBit(int mask){
        int ans=0;
        while(mask!=0){
            if(mask%2==1){
                ans++;
            }
            mask/=2;
        }
        return ans;
    }
    static boolean checkBit(int mask,int bit){
        int a=mask&(1<<bit);
        if(a!=0){
            return true;
        }
        else{
            return false;
        }
    }
    static void printMask(int mask){
        System.out.println(Integer.toBinaryString(mask));
    }
/***************************************Handling Deep Recursions*********************************/
    public static void main (String[] args) throws Exception {
        new Thread(null, new Main(), "Main", 1<<28).start();
    }
    public void run() {
    try{
        //do your work here
    }
        catch(Exception E){
            E.printStackTrace();
        }
    }
/*********************************************LinkedList***********************************************/
    static class LinkedList{
        int data;
        LinkedList next;
        LinkedList(int n){
            data=n;
            next=null;
        }
        public String toString(){
            return " "+data;
        }
    }
/******************************************Edge Graph*****************************************************/
    // static ArrayList<Integer> graph[],colo[];
    static void Makegraph(int n){
        graph=new ArrayList[n];
        colo=new ArrayList[n];
        for(int i=0;i<n;i++){
            graph[i]=new ArrayList<>();
            colo[i]=new ArrayList<>();
        }
    }
    static void addEdge(int a,int b,int i){
        graph[a].add(b);
        colo[a].add(i);
        graph[b].add(a);
        colo[b].add(i);
    }
}
/**********************************************Trie*****************************************************/
    static class Trie {
        static class TrieNode{
            char data;
            boolean isTerminating;
            TrieNode children[];
            int childCount;
            public TrieNode(char data) {
                this.data=data;
                isTerminating=false;
                children=new TrieNode[26];
                childCount=0;
            }
        }
        private TrieNode root;
        public int count;
        static String word="";
        public Trie() {
            root = new TrieNode('\0');
            count = 0;
        }	
        private boolean add(TrieNode root,int i){
            if(i==word.length()){
                if (!root.isTerminating) {
                    root.isTerminating = true;
                    return true;
                }
                else{
                    return false;
                }
            }		
            int childIndex = word.charAt(i) - 'a';
            TrieNode child = root.children[childIndex];
            if(child == null){
                child = new TrieNode(word.charAt(i));
                root.children[childIndex] = child;
                root.childCount++;
            }
            return add(child,i+1);
        }
        public void add(String words){
            word=words;
            if (add(root,0)) {
                this.count++;
            }
            word="";
        }
        public boolean search(TrieNode root,int i){
            if(i==word.length()){
                return root.isTerminating;
            }
            int childIndex=word.charAt(i)-'a';
            TrieNode child = root.children[childIndex];
            if(child!=null){
                return search(child,i+1);
            }
            return false;
        }
        public boolean search(String words){
            word=words;
            boolean ans=search(root,0);
            word="";
            return ans;
        }
    }
/**************************************Segment Tree with lazy prop*********************************/
    class segmentTree{
        int n=0;
        int[] lo,hi;
        long[] value,delta;
        segmentTree(int n){
            this.n=n;
            lo=new int[4*n+1];
            hi=new int[4*n+1];
            value=new long[4*n+1];
            delta=new long[4*n+1];
            init(1,1,n);
        } 
        void init(int i,int left,int right){
            lo[i]=left;
            hi[i]=right;
            if(left==right){
                return;
            }
            int mid=(left+right)/2;
            init(2*i,left,mid);
            init(2*i+1,mid+1,right);
        }
        void update(int left,int right,long val){
            update(1,left,right,val);
        }
        long updateFunction(long a,long b){
            long c=a+b;
            return c;
        }
        long queryFunction(long a,long b){
            long c=Math.min(a,b);
            return c;
        }
        long query(int left,int right){
            return query(1,left,right);
        }
        void prop(int i){
            delta[2*i]=updateFunction(delta[2*i],delta[i]);
            delta[2*i+1]=updateFunction(delta[2*i+1],delta[i]);
            delta[i]=0;
        }
        void update(int i){
            value[i]=queryFunction(updateFunction(value[2*i],delta[2*i]),updateFunction(value[2*i+1],delta[2*i+1]));
        }
        void update(int i,int left,int right,long val){
            if(left>hi[i]||right<lo[i]){
                return;
            }
            if(lo[i]>=left&&hi[i]<=right){
                delta[i]=updateFunction(delta[i],val);
                return;
            }
            prop(i);
            update(2*i,left,right,val);
            update(2*i+1,left,right,val);
            update(i);
        }
        long query(int i,int left,int right){
            if(left>hi[i]||right<lo[i]){
                return Integer.MAX_VALUE;
            }
            if(lo[i]>=left&&hi[i]<=right){
                return updateFunction(value[i],delta[i]);
            }
            prop(i);
            long l=query(2*i,left,right);
            long r=query(2*i+1,left,right);
            update(i);
            return queryFunction(l,r);
        }
    }
/*****************************************Normal Segment Tree**************************************/
    static class segmentTree{
        int n=0;
        int[] lo,hi;
        long[] value;
        segmentTree(int n){
            this.n=n;
            lo=new int[4*n+1];
            hi=new int[4*n+1];
            value=new long[4*n+1];
            init(1,1,n);
        } 
        void init(int i,int left,int right){
            lo[i]=left;
            hi[i]=right;
            if(left==right){
                return;
            }
            int mid=(left+right)/2;
            init(2*i,left,mid);
            init(2*i+1,mid+1,right);
        }
        void update(int left,int right,long val){
            update(1,left,right,val);
        }
        long updateFunction(long a,long b){
            long c=a+b;
            return c;
        }
        long queryFunction(long a,long b){
            long c=Math.min(a,b);
            return c;
        }
        long query(int left,int right){
            return query(1,left,right);
        }
        void update(int i){
            value[i]=queryFunction(value[2*i],value[2*i+1]);
        }
        void update(int i,int left,int right,long val){
            if(left>hi[i]||right<lo[i]){
                return;
            }
            if(lo[i]==hi[i]){
                value[i]=val;
                return ;
            }
            update(2*i,left,right,val);
            update(2*i+1,left,right,val);
            update(i);
        }
        long query(int i,int left,int right){
            if(left>hi[i]||right<lo[i]){
                return Integer.MAX_VALUE;
            }
            if(lo[i]>=left&&hi[i]<=right){
                return value[i];
            }
            long l=query(2*i,left,right);
            long r=query(2*i+1,left,right);
            return queryFunction(l,r);
        }
    }
/*****************************************Tree RMQ LCA*********************************************/
    static int first[],height[];
    static ArrayList<Integer> euler;
    static class segmentTree{
        int n=0;
        int[] lo,hi;
        int[] value;
        segmentTree(int n){
            this.n=n;
            lo=new int[4*n+1];
            hi=new int[4*n+1];
            value=new int[4*n+1];
            init(1,1,n);
        } 
        void init(int i,int left,int right){
            lo[i]=left;
            hi[i]=right;
            if(left==right){
                return;
            }
            int mid=(left+right)/2;
            init(2*i,left,mid);
            init(2*i+1,mid+1,right);
        }
        void update(int left,int right,int val){
            update(1,left,right,val);
        }
        
        int query(int left,int right){
            return query(1,left,right);
        }
        void update(int i,int left,int right,int val){
            if(left>hi[i]||right<lo[i]){
                return ;
            }
            if(lo[i]==hi[i]){
                value[i]=val;
                return ;
            }
            update(2*i,left,right,val);
            update(2*i+1,left,right,val);
            int l=value[2*i];
            int r=value[2*i+1];
            int ans=0;
            if(l==-1&&r==-1){
                ans=-1;
            }
            else if(l==-1){
                ans=r;
            }
            else if(r==-1){
                ans=l;
            }
            else{
                if(height[l]>=height[r]){
                    ans=r;
                }
                else{
                    ans=l;
                }
            }
            value[i]=ans;
            return;
        }
        int query(int i,int left,int right){
            if(left>hi[i]||right<lo[i]){
                return -1;
            }
            if(lo[i]>=left&&hi[i]<=right){
                return value[i];
            }
            int l=query(2*i,left,right);
            int r=query(2*i+1,left,right);
            int ans=0;
            if(l==-1&&r==-1){
                ans=-1;
            }
            else if(l==-1){
                ans=r;
            }
            else if(r==-1){
                ans=l;
            }
            else{
                if(height[l]>=height[r]){
                    ans=r;
                }
                else{
                    ans=l;
                }
            }
            return ans;
        }
    }
    static void dfs(int start,int par,int h){
        first[start]=euler.size();
        height[start]=h;
        euler.add(start);
        for(Pair p:graph[start]){
            if(p.u!=par){
                dfs(p.u,start,h+1);
                euler.add(start);
            }
        }
    }
/*********************************************BIT*************************************************/
    static class BIT{
        long input[];
        int n;
        BIT(int n){
            this.n=n;
            input=new long[n+1];
        }
        long query(int i){
            long sum=0;
            while(i>0){
                sum+=input[i];
                i-=(i&(-i));
            }
            return sum;
        }
        long query(int l,int r){
            return query(r)-query(l-1);
        }
        void update(int i,long val){
            while(i<=n){
                input[i]+=val;
                i+=(i&(-i));
            }
        }
    }
/********************************************KMP********************************************************/
     private static int[] lpss(String pattern){
         String str=pattern;
         int n=str.length();
         int lps[]=new int[n];
         int len=0;
         int i=1;
         lps[0]=0;
         
         while(i<n){
             if(str.charAt(i)==str.charAt(len)){
                 len++;
                 lps[i]=len;
                 i++;
                 
             }
             else{
                 if(len!=0){
                     len=lps[len-1];
                 }
                 else{
                     i++;
                 }
             }
         }
         return lps;
     }
            String pat=st[k];
            String txt=st[k+1];
            int M = pat.length(); 
            int N = sz; 
            int lps[] = lpss(pat); 
            int j = 0; 
            int i = 0;
            int cnt=0;
            while (i < N) { 
                if (pat.charAt(j) == txt[i]) { 
                    j++; 
                    i++; 
                } 
                if (j == M) { 
                    cnt=M;
                    break; 
                } 
                else if (i < N && pat.charAt(j) != txt[i]) { 
                    if (j !=0) 
                        j = lps[j-1]; 
                    else
                        i = i + 1; 
                }
            } 
/*****************************************MO's Algorithm**************************************************/
        String st[]=nl();
        n=pi(st[0]);
        sq=(int)Math.sqrt(n)+1;    
        int rem[]=new int[n+1];
        st=nl();
        for(int i=1;i<=n;i++){
            rem[i]=pi(st[i-1]);
        }
        st=nl();
        int q=pi(st[0]);
        // debug(rem);
        Pairkl query[]=new Pairkl[q];
        for(int i=0;i<q;i++){
            st=nl();
            int l=pi(st[0]);
            int r=pi(st[1]);
            query[i]=new Pairkl(l,r,i);
        }
        Arrays.sort(query,new PairCompkl());
        long ans[]=new long[q];
        int z=1000005;
        freq=new int[z];
        bucket=new int[z/sq];
        
        int l=1;
        int r=1;
        add(rem[1]);
       // debug(query);

        for(int i=0;i<q;i++){
            int rl=query[i].u;
            int rr=query[i].v;
            while(r<rr){
                r++;
                add(rem[r]);
            }
            while(l<rl){
                sub(rem[l]);
                l++;
            }
            while(l>rl){
                l--;
                add(rem[l]);
            }
         
            while(r>rr){
                sub(rem[r]);
                r--;
            }
            //debug(freq);
            ans[query[i].i]=get();
        }
        for(int i=0;i<q;i++){
            out.println(ans[i]);
        }    
    static class PairCompkl implements Comparator<Pairkl>{
        public int compare(Pairkl p1,Pairkl p2){
            int aa=p1.u/sq;
            int bb=p2.u/sq;
            if(aa!=bb){
                return aa-bb;
            }
            else{
                if((aa&1)==1)
                return p1.v-p2.v;
                else
                return p2.v-p1.v;
            }
        }
    }
    static class Pairkl implements Comparable<Pairkl> {
        int u;
        int v;
        int i;
        int index=-1;
        public Pairkl(int u, int v,int i) {
            this.u = u;
            this.v = v;
            this.i = i;
        }
 
        public int hashCode() {
            int hu = (int) (u ^ (u >>> 32));
            int hv = (int) (v ^ (v >>> 32));
            return 31 * hu + hv;
        }
 
        public boolean equals(Object o) {
            Pairkl other = (Pairkl) o;
            return u == other.u && v == other.v;
        }
 
        public int compareTo(Pairkl other) {
            if(index!=other.index)
                return Long.compare(index, other.index);
            return Long.compare(v, other.v)!=0?Long.compare(v, other.v):Long.compare(u, other.u);
        }
 
        public String toString() {
            return "[u=" + u + ", v=" + v +", "+i+ "]";
        }
    }
    static int freq[],n,sq,bucket[];
    static HashSet<Integer> curAns=new HashSet<>();
    static void add(int a){
        int bb=a/sq;
        if(freq[a]==1){
            bucket[bb]--;
        }
        freq[a]++;
        if(freq[a]==1){
            bucket[bb]++;
        }
      //  debug(curAns);
    }
    static void sub(int a){
        int bb=a/sq;
        if(freq[a]==1){
            bucket[bb]--;
        }
        freq[a]--;
        if(freq[a]==1){
            bucket[bb]++;
        }
      //  debug(curAns);
    }
    static int get(){
        for(int i=0;i<bucket.length;i++){
            if(bucket[i]!=0){
                for(int j=Math.max(0,i*sq-10);j<=(i+1)*sq&&j<freq.length;j++){
                    if(freq[j]==1)return j;
                }
            }
        }
        return 0;
    }
/**********************************Minimum Index Query With Lazy Prop****************************/
    static class segmentTree{
        int n=0;
        int[] lo,hi;
        long[] value,delta;
        int idx[];
        segmentTree(int n){
            this.n=n;
            lo=new int[8*n+1];
            hi=new int[8*n+1];
            value=new long[8*n+1];
            delta=new long[8*n+1];
            init(1,1,n);
            idx=new int[8*n+1];
        } 
        void init(int i,int left,int right){
            lo[i]=left;
            hi[i]=right;
            if(left==right){
                return;
            }
            int mid=(left+right)/2;
            init(2*i,left,mid);
            init(2*i+1,mid+1,right);
        }
        void update(int left,int right,long val){
            update(1,left,right,val);
        }
        Pairl query(int left,int right){
            return query(1,left,right);
        }
        void prop(int i){
            delta[2*i]+=delta[i];
            delta[2*i+1]+=delta[i];
            delta[i]=0;
        }
        void update(int i){
            long a=value[2*i]+delta[2*i];
            long b=value[2*i+1]+delta[2*i+1];
            if(a<=b){
                value[i]=a;
                if(a==b){
                    idx[i]=Math.max(idx[2*i],idx[2*i+1]);
                }
                else{
                    idx[i]=idx[2*i];
                }
            }
            else{
                value[i]=b;
                idx[i]=idx[2*i+1];
            }
        }
        void update(int i,int left,int right,long val){
            if(left>hi[i]||right<lo[i]){
                return;
            }
            if(lo[i]>=left&&hi[i]<=right){
                delta[i]+=val;
                value[i]+=delta[i];
                
                if(left==right){
                    delta[i]=0;
                    idx[i]=left;
                    return;
                }
                prop(i);
                return;
            }
            prop(i);
            update(2*i,left,right,val);
            update(2*i+1,left,right,val);
            update(i);
        }
        Pairl query(int i,int left,int right){
            if(left>hi[i]||right<lo[i]){
                return new Pairl(Long.MAX_VALUE,-1);
            }
            if(lo[i]>=left&&hi[i]<=right){
                value[i]+=delta[i];
                prop(i);
                return new Pairl(value[i],idx[i]);
            }
            prop(i);
            Pairl l=query(2*i,left,right);
            Pairl r=query(2*i+1,left,right);
            update(i);
            if(l.u<=r.u){
                if(l.u==r.u){
                    return new Pairl(l.u,Math.max(r.v,l.v));
                }
                else{
                    return l;
                }
            }
            else{
                return r;
            }
        }
    }
/***************************************Range Sum Query Segment Tree***********************************/
    static class segmentTree{
        int n=0;
        int[] lo,hi;
        long[] value,delta;
        segmentTree(int n){
            this.n=n;
            lo=new int[8*n+1];
            hi=new int[8*n+1];
            value=new long[8*n+1];
            delta=new long[8*n+1];
            init(1,1,n);
        } 
        void init(int i,int left,int right){
            lo[i]=left;
            hi[i]=right;
            if(left==right){
                return;
            }
            int mid=(left+right)/2;
            init(2*i,left,mid);
            init(2*i+1,mid+1,right);
        }
        void update(int left,int right,long val){
            update(1,left,right,val);
        }
        long query(int left,int right){
            return query(1,left,right);
        }
        void prop(int i){
            delta[2*i]+=delta[i];
            delta[2*i+1]+=delta[i];
            delta[i]=0;
        }
        void update(int i){
            long a=value[2*i]+delta[2*i];
            long b=value[2*i+1]+delta[2*i+1];
            value[i]=a+b;
        }
        void update(int i,int left,int right,long val){
            if(left>hi[i]||right<lo[i]){
                return;
            }
            if(lo[i]>=left&&hi[i]<=right){
                delta[i]+=val;
                value[i]+=delta[i]*(hi[i]-lo[i]+1);
                
                if(left==right){
                    delta[i]=0;
                    return;
                }
                prop(i);
                return;
            }
            prop(i);
            update(2*i,left,right,val);
            update(2*i+1,left,right,val);
            update(i);
        }
        long query(int i,int left,int right){
            if(left>hi[i]||right<lo[i]){
                return 0;
            }
            if(lo[i]>=left&&hi[i]<=right){
                value[i]+=delta[i]*(hi[i]-lo[i]+1);
                prop(i);
                return value[i];
            }
            prop(i);
            long l=query(2*i,left,right);
            long r=query(2*i+1,left,right);
            update(i);
            return l+r;
        }
    }
/***************************************checking Memory and Time taken*******************************/
        long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long before=System.currentTimeMillis();
        //code 
        long after=System.currentTimeMillis();
        long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long actualMemUsed=afterUsedMem-beforeUsedMem;
        long used=after-before;
        debug(actualMemUsed/(1024*1024));
        debug(used);
/*****************************************Radix Sort**************************************************/
	public static int[] radixSort(int[] f){ return radixSort(f, f.length); }
	public static int[] radixSort(int[] f, int n)
	{
		int[] to = new int[n];
		{
			int[] b = new int[65537];
			for(int i = 0;i < n;i++)b[1+(f[i]&0xffff)]++;
			for(int i = 1;i <= 65536;i++)b[i]+=b[i-1];
			for(int i = 0;i < n;i++)to[b[f[i]&0xffff]++] = f[i];
			int[] d = f; f = to;to = d;
		}
		{
			int[] b = new int[65537];
			for(int i = 0;i < n;i++)b[1+(f[i]>>>16)]++;
			for(int i = 1;i <= 65536;i++)b[i]+=b[i-1];
			for(int i = 0;i < n;i++)to[b[f[i]>>>16]++] = f[i];
			int[] d = f; f = to;to = d;
		}
		return f;
	}
/*******************************************Topo Sort****************************************************/
        int indeg[]=new int[n+1];
        Makegraph(n+1);
        ArrayList<Integer> list=new ArrayList<>();
        for(int i=0;i<m;i++){
            st=nl();
            int a=pi(st[0]);
            int b=pi(st[1]);
            addEdge(a,b);
            list.add(a);
            list.add(b);
            indeg[b]++;
        }
        Queue<Integer> q=new ArrayDeque<>();
        vis=new boolean[n+1];
        for(int i=1;i<=n;i++){
            if(indeg[i]==0){
                q.add(i);
                vis[i]=true;
            }
        }
        int ans=0;
        boolean flag=true;
        if(q.size()==0){
            flag=false;
        }
        while(!q.isEmpty()){
            int sz=q.size();
            ans++;
            while(sz>0){
                int a=q.remove();
                sz--;
                for(Pair p:graph[a]){
                    if(vis[p.u]){
                        debug(p.u);
                        flag=false;
                        break;
                    }
                    indeg[p.u]--;
                    if(indeg[p.u]==0){
                        q.add(p.u);
                    }
                }
                if(!flag)break;
            }
            if(!flag)break;         
        }

       /// debug(graph);
        for(int i=1;i<=n;i++){
            if(indeg[i]!=0){
                flag=false;
                break;
            }
        }
/***************************************Binary Lifting LCA***********************************************/
    static int D[],L[][],ln,q,n;
    static void dfs1(int start,int par,int d){
        D[start]=d;
        L[start][0]=par;
        for(int i=1;i<ln;i++){
            L[start][i]=L[L[start][i-1]][i-1];
        }
        for(Pair p:graph[start]){
            if(p.u==par)continue;
            dfs1(p.u,start,d+1);
        }
    }
    static int lca(int u,int v){
        if(D[u]<D[v]){
            int t=u;
            u=v;
            v=t;
        }
        int diff=D[u]-D[v];
        for(int i=0;i<ln;i++){
            if((diff&(1<<i))!=0){
                u=L[u][i];
            }
        }
        if(u==v)return u;
        for(int i=ln-1;i>=0;i--){
            if(L[u][i]!=L[v][i]){
                u=L[u][i];
                v=L[v][i];
            }
        }
        return L[u][0];
    }
/*****************************************Discrete Logarithm************************************************/
    static long discreteLog(long a,long b,long m){
        long w=(long)Math.sqrt(m)+1;
        long aw=modulo(a,w,mod);
        long cur=aw;
        HashMap<Long,Long> map=new HashMap<>();
        for(int i=1;i<=w;i++){
            if(!map.containsKey(cur)){
                map.put(cur,(long)i);
            }
            cur=(cur*aw)%m;
        }
        long bw=b;
        for(int i=0;i<=w;i++){
            if(map.containsKey(bw)){
                return w*map.get(bw)-i;
            }
            bw=(bw*a)%mod;
        }
        return -1l;
    }
/******************************************MultiHashSet*************************************************/
    static class MultiHashSet<K>{
        HashMap<K,Integer> map;
        int sz;
        MultiHashSet(){
            map=new HashMap<>();
            sz=0;
        }
        boolean contains(K element){
            return map.containsKey(element);
        }
        void add(K element){
            map.put(element,map.getOrDefault(element,0)+1);
            sz++;
        }        
        void remove(K element){
            int u=map.get(element);
            map.put(element,u-1);
            if(u==1){
                map.remove(element);
            }
            sz--;
        }
        int size(){
            return sz;
        }
        ArrayList<K> asArray(){
            ArrayList<K> list=new ArrayList<>();
            for(K u:map.keySet()){
                int cur=map.get(u);
                while(cur-->0){
                    list.add(u);
                }
            }
            return list;
        }
    }
/****************************************Euler Totient Function******************************************/
        long f[]=new long[w];
        for(int i=0;i<w;i++){
            f[i]=i;
        }
        for(int i=2;i<w;i++){
            if(f[i]==i){
                f[i]=i-1;
                for(int j=2*i;j<w;j+=i){
                    f[j]/=i;
                    f[j]*=(i-1);
                }
            }
        }
/***************************************Mobius inversion function*********************************/
        long mu[]=new long[w];
        Arrays.fill(mu,-2);
        mu[0]=0;
        mu[1]=1;
        for(int i=2;i<w;i++){
            if(mu[i]==-2){
                for(int j=2*i;j<w;j+=i){
                    mu[j]++;
                }
            }
        }
        for(int i=2;i<w;i++){
            if(mu[i]!=-2){
                if(mu[i]%2==0){
                    mu[i]=1;
                }
                else{
                    mu[i]=-1;
                }
            }
        }
        for(int i=2;i<w;i++){
            if(mu[i]==-2){
                mu[i]=-1;
                long u=i;
                u=u*u;
                if(u<0||u>=w)continue;
                for(int j=(int)u;j<w;j+=u){
                    mu[j]=0;
                }
            }
        }
        ///////GUPTA'S Approach

        // long mu[]=new long[w];
        // ArrayList<Integer> primes=new ArrayList<>();
        // int prime[]=new int[w];
        // Arrays.fill(prime,1);
        // mu[1] = 1;
        // for(int i=2;i<w;i++)
        // {
        //     if(prime[i]!=0){
        //         primes.add(i); 
        //         mu[i] = -1;
        //     }
        //     for(int it: primes)
        //     {
        //         if(i * it >= w)
        //             break;
        //         prime[i * it] = 0;
        //         if(i % it == 0)
        //         {
        //             mu[i * it] = 0;
        //             break;
        //         }
        //         else
        //             mu[i * it] = mu[i] * mu[it];
        //     }
        // }
/************************************Exploiting the square root property of n/g***********************/
        ArrayList<Integer> divs[]=new ArrayList[w];
        for(int i=0;i<w;i++){
            divs[i]=new ArrayList<>();
        }
        for(int i=1;i<w;i++){
            for(int j=i;j<w;j+=i){
                divs[j].add(i);
            }
        }
        long cnt[]=new long[w];
        for(int i=1;i<w;i++){
            long a=0;
            for(int j:divs[i]){
                int u=i/j;
                //int u1=u-1;
                long oo=(nc[u]-nc[u-1]+mod)%mod;
                a=(a+(oo*mu[j]))%mod;
                a=(a+mod)%mod;
            }
            cnt[i]=(cnt[i-1]+a)%mod;
        }
        long ans[]=new long[w];
        for(int i=1;i<w;i++){
            long a=0;
            for(int j:divs[i]){
                int u=i/j;
                long j1=j;
                long uu=(cnt[u]-cnt[u-1]+mod)%mod;
                a=(a+(j1*uu)%mod)%mod;
                a=(a+mod)%mod;
            }
            ans[i]=(ans[i-1]+a)%mod;
        }
/****************************************Merge Sort Tree*************************************************/
    static class segmentTree{
        int n=0;
        int[] lo,hi;
        ArrayList<Long> tr[];
        segmentTree(int n){
            this.n=n;
            lo=new int[4*n+1];
            hi=new int[4*n+1];
            tr=new ArrayList[4*n+1];
            init(1,1,n);
        }
        ArrayList<Long> merge(ArrayList<Long> t1,ArrayList<Long> t2){
            if(t1==null){
                return t2;
            }
            if(t2==null){
                return t2;
            }
            ArrayList<Long> tt=new ArrayList<>();
            int i=0;
            int j=0;
            while(i<t1.size()&&j<t2.size()){
                if(t1.get(i)<t2.get(j)){
                    tt.add(t1.get(i++));
                }
                else{
                    tt.add(t2.get(j++));
                }
            }
            while(i<t1.size()){
                tt.add(t1.get(i++));
            }
            while(j<t2.size()){
                tt.add(t2.get(j++));
            }
            return tt;
        }
        void init(int i,int left,int right){
            lo[i]=left;
            hi[i]=right;
            if(left==right){
                if(tr[i]==null){
                    tr[i]=new ArrayList<>();
                }
                tr[i].add();//Add the elements of array you want to query
                return;
            }
            int mid=(left+right)/2;
            init(2*i,left,mid);
            init(2*i+1,mid+1,right);
            tr[i]=merge(tr[2*i],tr[2*i+1]);
        }        
        long query(int left,int right,long k){
            return query(1,left,right,k);
        }
        long query(int i,int left,int right,long k){
          
            if(left>hi[i]||right<lo[i]){
                return 0;
            }
            if(lo[i]>=left&&hi[i]<=right){
                if(tr[i]==null)return 0;
                int start=0;
                int end=tr[i].size()-1;
                int cur=-1;
                while(start<=end){
                    int mid=(start+end)/2;
                    if(tr[i].get(mid)>=k){
                        end=mid-1;
                        cur=mid;
                    }
                    else{
                        start=mid+1;
                    }
                }
                if(cur==-1)return 0;
                return tr[i].size()-cur;
            }
            long l=query(2*i,left,right,k);
            long r=query(2*i+1,left,right,k);
            return l+r;
        }
    }
/********************************************End***********************************************************/