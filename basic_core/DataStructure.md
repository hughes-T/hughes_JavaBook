# 数据结构

## 常用结构

### 数组

> Array is King
- 内存需要连续的空间
- 查询效率高，时间复杂度为 O（1）
- 增删比较消耗系统性能，且需要动态扩容，时间复杂度为 O（n）

### 链表

- 内存不需要连续空间
- 增删效率高，时间复杂度为 O（1）
- 查询比较消耗性能，时间复杂度为 O（n）

### 红黑树 RBT

> 模拟小网站 https://www.cs.usfca.edu/~galles/visualization/RedBlack.html

自平衡的二叉树，是二叉树平衡问题的一种解决方案，目标是**保持黑平衡**

它的增删改查的平均时间复杂度为O（log2n）

规则：

1. 根节点为黑
2. 插入时为红
3. 红不相连
4. 每个路径的黑色数量一致

场景：

- 首节点为黑
- 父节点为黑时，直接插入
- 叔父节点为红，叔父变为黑色，**祖父重新插入原位置**（默认红色，可能触发其他场景）
- 父为红，叔节点不存在或为黑时：
  - 左左 右旋 父变黑，祖变红
  - 左右 先进行一次不变色的左旋变为为上一种情况，再执行一次右旋
  - 右右 和 右左 与以上的情况相似

## JDK中的集合

### ArrayList源码

**filed 字段**

```java
/**
 * Default initial capacity.
 	默认初始容量
 */
private static final int DEFAULT_CAPACITY = 10;

/**
 * Shared empty array instance used for empty instances.
 	空集合，用于无参构造时使用
 */
private static final Object[] EMPTY_ELEMENTDATA = {};

/**
 * Shared empty array instance used for default sized empty instances. We
 * distinguish this from EMPTY_ELEMENTDATA to know how much to inflate when
 * first element is added.
 	传参构造为0时会使用
 */
private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

/**
 * The array buffer into which the elements of the ArrayList are stored.
 * The capacity of the ArrayList is the length of this array buffer. Any
 * empty ArrayList with elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA
 * will be expanded to DEFAULT_CAPACITY when the first element is added.
 	存储数据的数组对象
 */
transient Object[] elementData; // non-private to simplify nested class access

/**
 * The size of the ArrayList (the number of elements it contains).
 *
 * @serial
 	数组大小
 */
private int size;
```

**add方法**

- 核心思路是容量不足时执行扩容

```java
private void grow(int minCapacity) {
    // overflow-conscious code
    int oldCapacity = elementData.length;
    // 加载因子 new = old + old >> 1
    int newCapacity = oldCapacity + (oldCapacity >> 1);
    if (newCapacity - minCapacity < 0)
        newCapacity = minCapacity;
    if (newCapacity - MAX_ARRAY_SIZE > 0)
        newCapacity = hugeCapacity(minCapacity);
    // minCapacity is usually close to size, so this is a win:
    //将原数组copy到扩容数组
    elementData = Arrays.copyOf(elementData, newCapacity);
}
```

**remove方法**

- 核心思路是将目标索引+1以后的元素往前copy，尾部删除，相当于剪切后段，往前移一位粘贴

```java
public E remove(int index) {
    rangeCheck(index);

    modCount++;
    E oldValue = elementData(index);

    int numMoved = size - index - 1;
    if (numMoved > 0)
        //源数组 目标下标 目标数组 目标下标 数组长度
        System.arraycopy(elementData, index+1, elementData, index,
                         numMoved);
    elementData[--size] = null; // clear to let GC do its work

    return oldValue;
}
```

**FailFast机制**

快速失败机制，java集合类为防止迭代过程中内部结构发生改变的一种设计，这种检查机制在并发情况可能会抛出java.util.ConcurrentModificationException 异常

```java
private class Itr implements Iterator<E> {
    int cursor;       // index of next element to return
    int lastRet = -1; // index of last element returned; -1 if no such
    int expectedModCount = modCount;
    
	final void checkForComodification() {
    	if (modCount != expectedModCount)
        	throw new ConcurrentModificationException();
		}
}
```

### LinkedList源码

是双向链表的实现

**内部静态类Node**

```java
private static class Node<E> {
    E item;
    Node<E> next;
    Node<E> prev;

    Node(Node<E> prev, E element, Node<E> next) {
        this.item = element;
        this.next = next;
        this.prev = prev;
    }
}
```

**add方法** 

copy首指针，新建Node将next指针指向copy指针，将首指针重新指向新建Node

```java
private void linkFirst(E e) {
    final Node<E> f = first;
    final Node<E> newNode = new Node<>(null, e, f);
    first = newNode;
    if (f == null)
        last = newNode;
    else
        f.prev = newNode;
    size++;
    modCount++;
}

private void linkLast(E e) {
    final Node<E> l = last;
    final Node<E> newNode = new Node<>(l, e, null);
    last = newNode;
    if (l == null)
         first = newNode;
     else
         l.next = newNode;
     size++;
     modCount++;
}
```

**get方法**

长度是否过半，选择短端探索

```java
Node<E> node(int index) {
    // assert isElementIndex(index);

    if (index < (size >> 1)) {
        Node<E> x = first;
        for (int i = 0; i < index; i++)
            x = x.next;
        return x;
    } else {
        Node<E> x = last;
        for (int i = size - 1; i > index; i--)
            x = x.prev;
        return x;
    }
}
```

**set方法**

```java
public E set(int index, E element) {
    checkElementIndex(index);
    Node<E> x = node(index);
    E oldVal = x.item;
    x.item = element;
    return oldVal;
}
```

### Vector

Vector线程是安全的，里面所有的操作都加了synchronized

对性能消耗比较到，后来被慢慢舍弃了

ArrayList ：Vector = HashMap ：HashTable 

如果一定使用的话，可以使用Collections.synchronizedList()对List进行封装，增加代码的灵活度

### HashSet 和 TreeSet

HashSet 内部由一个Hash表（HashMap）实现的

add时 put（k，默认v）

TreeSet 内部由TreeMap实现的

### TreeMap

TreeMap 实现了红黑树

**核心静态类 Entry**

```java
static final class Entry<K,V> implements Map.Entry<K,V> {
    K key;
    V value;
    Entry<K,V> left;//左节点
    Entry<K,V> right;//右节点
    Entry<K,V> parent;//父节点
    boolean color = BLACK;//黑 or 红

    /**
     * Make a new cell with given key, value, and parent, and with
     * {@code null} child links, and BLACK color.
     */
    Entry(K key, V value, Entry<K,V> parent) {
        this.key = key;
        this.value = value;
        this.parent = parent;
    }
}
```

**put方法**

```java
//此段为二叉树实现
Entry<K,V> t = root;//实时位置

if (t == null) {
    compare(key, key); // type (and possibly null) check
    root = new Entry<>(key, value, null);
    size = 1;
    modCount++;
    return null;
}

int cmp;//对比结果
Entry<K,V> parent;//实时父节点

       Comparable<? super K> k = (Comparable<? super K>) key;
            do {
                parent = t;
                cmp = k.compareTo(t.key);
                if (cmp < 0)
                    t = t.left;
                else if (cmp > 0)
                    t = t.right;
                else
                    return t.setValue(value);
            } while (t != null);		//寻找最终parent位置，期间出现key碰撞时，覆盖掉原节点

 Entry<K,V> e = new Entry<>(key, value, parent);	//插入新节点
        if (cmp < 0)
            parent.left = e;
        else
            parent.right = e;
		//此方法实现了红黑树规则
        fixAfterInsertion(e);
```

> 为什么要有 K-V 数据结构，如果它只是实现索引概念，数组链表似乎也能做到？
>
> K-V对比数组链表这种单列，它没有像单列那样简单粗暴地把元素堆叠起来，而是先确立了存储的逻辑顺序（比如二叉树，红黑树），使得它的增删改查的时间复杂度都是O(log2n)，KV设计是Nosql的一种实现方式

```java
private void fixAfterInsertion(Entry<K,V> x) {
    x.color = RED;

    while (x != null && x != root && x.parent.color == RED) {
        if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
            Entry<K,V> y = rightOf(parentOf(parentOf(x)));
            if (colorOf(y) == RED) {
                setColor(parentOf(x), BLACK);
                setColor(y, BLACK);
                setColor(parentOf(parentOf(x)), RED);
                x = parentOf(parentOf(x));
            } else {
                if (x == rightOf(parentOf(x))) {
                    x = parentOf(x);
                    rotateLeft(x);
                }
                setColor(parentOf(x), BLACK);
                setColor(parentOf(parentOf(x)), RED);
                rotateRight(parentOf(parentOf(x)));
            }
        } else {
            Entry<K,V> y = leftOf(parentOf(parentOf(x)));
            if (colorOf(y) == RED) {
                setColor(parentOf(x), BLACK);
                setColor(y, BLACK);
                setColor(parentOf(parentOf(x)), RED);
                x = parentOf(parentOf(x));
            } else {
                if (x == leftOf(parentOf(x))) {
                    x = parentOf(x);
                    rotateRight(x);
                }
                setColor(parentOf(x), BLACK);
                setColor(parentOf(parentOf(x)), RED);
                rotateLeft(parentOf(parentOf(x)));
            }
        }
    }
    root.color = BLACK;
}
```

### HashMap源码

> 为什么HashMap做为面试题经久不衰
>
> 它是多种数据结构的经典结合，对其设计的理解能够反映出一个人对于数据结构的感悟

**HashMap的设计，主干利用了数组的定位优势进行hash散列，又利用链表的增删优势处理hash碰撞；**

**在1.8中链表转红黑树，是对查询做了优化**

链表超过8，若数组长度未到达64，先进行数组扩容，数组若是超过64，则链表转红黑树。

数组扩容条件，容量超过0.75扩容因子（1.8中，链表数量超过8且数组容量小于64也会触发）

数组扩容过程 JDK1.7时，数组扩容时需要调用rehash方法，1.8时增加了寻找，每次2倍扩容，扩容后寻址算法结果不同的数据才迁移到高位

**元素设计 Node**

```java
static class Node<K,V> implements Map.Entry<K,V> {
    final int hash;	//散列值
    final K key; 
    V value;
    Node<K,V> next;//链表情况下下个元素的指向

    Node(int hash, K key, V value, Node<K,V> next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }
}
```

**链表转红黑树的 TreeNode**

```java
static final class TreeNode<K,V> extends LinkedHashMap.Entry<K,V> {
    TreeNode<K,V> parent;  // red-black tree links
    TreeNode<K,V> left;
    TreeNode<K,V> right;
    TreeNode<K,V> prev;    // needed to unlink next upon deletion
    boolean red;
    TreeNode(int hash, K key, V val, Node<K,V> next) {
        super(hash, key, val, next);
    }
}
```

**主字段**

```java
//HashMap的主干数组，可以看到就是一个Entry数组，初始值为空数组{}，主干数组的长度一定是2的次幂。
transient Node<K,V>[] table;

/**实际存储的key-value键值对的个数*/
transient int size;

/**阈值，当table == {}时，该值为初始容量（初始容量默认为16）；当table被填充了，也就是为table分配内存空间后，
threshold一般为 capacity*loadFactory。HashMap在进行扩容时需要参考threshold，后面会详细谈到*/
int threshold;

/**负载因子，代表了table的填充度有多少，默认是0.75
加载因子存在的原因，还是因为减缓哈希冲突，如果初始桶为16，等到满16个元素才扩容，某些桶里可能就有不止一个元素了。
所以加载因子默认为0.75，也就是说大小为16的HashMap，到了第13个元素，就会扩容成32。
*/
final float loadFactor;
```

简单来说，**HashMap由数组+链表组成的**，数组是HashMap的主体，链表则是主要为了解决哈希冲突而存在的，如果定位到的数组位置不含链表（当前entry的next指向null）,那么查找，添加等操作很快，仅需一次寻址即可；如果定位到的数组包含链表，对于添加操作，其时间复杂度为O(n)，首先遍历链表，存在即覆盖，否则新增；对于查找操作来讲，仍需遍历链表，然后通过key对象的equals方法逐一比对查找。

![](..\resources\image\hashMap.jpg)

**put方法**

```java
public V put(K key, V value) {
    return putVal(hash(key), key, value, false, true);
}
```

**hash方法**

```java
static final int hash(Object key) {
    int h;
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);//本身hash值的1-16位和17-32位进行异或运算,减少hash碰撞的概率
}
```

**putVal方法**

```java
final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
               boolean evict) {
    Node<K,V>[] tab; Node<K,V> p; int n, i;
    if ((tab = table) == null || (n = tab.length) == 0)
        n = (tab = resize()).length;//初始数组为空,进行一次扩容
    if ((p = tab[i = (n - 1) & hash]) == null)//散列位置为空,直接插入
        tab[i] = newNode(hash, key, value, null);
    else {
        //hash碰撞的情况
        Node<K,V> e; K k;
        if (p.hash == hash &&	//key相同直接置换
            ((k = p.key) == key || (key != null && key.equals(k))))
            e = p;
        else if (p instanceof TreeNode)//红黑树的情况,实现思路与TreeMap相同
            e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
        else {
            for (int binCount = 0; ; ++binCount) {//链表情况
                if ((e = p.next) == null) {
                    p.next = newNode(hash, key, value, null); //插入末位
                    if (binCount >= TREEIFY_THRESHOLD - 1) // 超过8转红黑树,但是数组不足64会进行扩容处理
                        treeifyBin(tab, hash);
                    break;
                }
                if (e.hash == hash &&			//期间对比key,若key相等,则直接替换value
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    break;
                p = e;
            }
        }
        if (e != null) { // existing mapping for key
            V oldValue = e.value;
            if (!onlyIfAbsent || oldValue == null)
                e.value = value;
            afterNodeAccess(e);
            return oldValue;
        }
    }
    ++modCount;
    if (++size > threshold)//数组扩容阈值判断
        resize();
    afterNodeInsertion(evict);
    return null;
}
```

**扩容方法**

```java
final Node<K,V>[] resize() {
    Node<K,V>[] oldTab = table;
    int oldCap = (oldTab == null) ? 0 : oldTab.length;
    int oldThr = threshold;
    int newCap, newThr = 0;
    if (oldCap > 0) {
        if (oldCap >= MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return oldTab;
        }
        else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                 oldCap >= DEFAULT_INITIAL_CAPACITY)
            newThr = oldThr << 1; // double threshold
    }
    else if (oldThr > 0) // initial capacity was placed in threshold
        newCap = oldThr;
    else {               // 原数组为空扩容为默认值
        newCap = DEFAULT_INITIAL_CAPACITY;
        newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
    }
    if (newThr == 0) {
        float ft = (float)newCap * loadFactor;
        newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
                  (int)ft : Integer.MAX_VALUE);
    }
    threshold = newThr;
    @SuppressWarnings({"rawtypes","unchecked"})
    Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
    table = newTab;
    if (oldTab != null) {	//原数组不为空对元素进行迁移
        for (int j = 0; j < oldCap; ++j) {
            Node<K,V> e;
            if ((e = oldTab[j]) != null) {
                oldTab[j] = null;
                if (e.next == null)
                    newTab[e.hash & (newCap - 1)] = e;//只有一个元素,重新进行hash
                else if (e instanceof TreeNode)
                    ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);//红黑树情况执行拆树,后面分析
                else { // preserve order
                    /**
                    链表情况的扩容
                    n 为扩容前的数量, 扩容时保证了它是2的次幂,比如n为16,二进制则为10000,n-1 为 1111
                    put时是进行的(n-1) & hash 散列,此处使用 n & hash 核验是否迁移至高位
                    因为每次扩容都是2倍扩容,相当于n的二进制前一位多了一个1
                    HashMap强大之处在于2的次幂扩容时链表高位迁移的逻辑闭环!!!
                    */
                    Node<K,V> loHead = null, loTail = null;
                    Node<K,V> hiHead = null, hiTail = null;
                    Node<K,V> next;
                    do {
                        next = e.next;
                        if ((e.hash & oldCap) == 0) {	//在原低位的第五位一定是0...
                            if (loTail == null)			
                                loHead = e;				
                            else						
                                loTail.next = e;
                            loTail = e;
                        }
                        else {							//需要高位迁移的第五位一定是1,但是但是
                            if (hiTail == null)			//它们之前的最后4位hash值是一样的!!!,所以仅仅是第五位1和0
                                hiHead = e;				//不同!!!
                            else
                                hiTail.next = e;
                            hiTail = e;
                        }
                    } while ((e = next) != null);
                    if (loTail != null) {
                        loTail.next = null;
                        newTab[j] = loHead;
                    }
                    if (hiTail != null) {
                        hiTail.next = null;
                        newTab[j + oldCap] = hiHead;
                    }
                }
            }
        }
    }
    return newTab;
}
```

**split方法**

红黑树迁移思路和链表一致，高低位迁移，容量小于阈值6时，红黑树转回链表

```java
final void split(HashMap<K,V> map, Node<K,V>[] tab, int index, int bit) {
    TreeNode<K,V> b = this;
    // Relink into lo and hi lists, preserving order
    TreeNode<K,V> loHead = null, loTail = null;
    TreeNode<K,V> hiHead = null, hiTail = null;
    int lc = 0, hc = 0;
    for (TreeNode<K,V> e = b, next; e != null; e = next) {
        next = (TreeNode<K,V>)e.next;
        e.next = null;
        if ((e.hash & bit) == 0) {
            if ((e.prev = loTail) == null)
                loHead = e;
            else
                loTail.next = e;
            loTail = e;
            ++lc;
        }
        else {
            if ((e.prev = hiTail) == null)
                hiHead = e;
            else
                hiTail.next = e;
            hiTail = e;
            ++hc;
        }
    }

    if (loHead != null) {
        if (lc <= UNTREEIFY_THRESHOLD)	//转回链表阈值,6
            tab[index] = loHead.untreeify(map);
        else {
            tab[index] = loHead;
            if (hiHead != null) // (else is already treeified)
                loHead.treeify(tab);
        }
    }
    if (hiHead != null) {
        if (hc <= UNTREEIFY_THRESHOLD)
            tab[index + bit] = hiHead.untreeify(map);
        else {
            tab[index + bit] = hiHead;
            if (loHead != null)
                hiHead.treeify(tab);
        }
    }
}
```