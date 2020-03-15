package javaoffer;

import org.junit.Test;

import java.util.*;

/**
 * 设计和构建一个“最近最少使用”缓存，该缓存会删除最近最少使用的项目。
 * 缓存应该从键映射到值(允许你插入和检索特定键对应的值)，并在初始化时指定最大容量。
 * 当缓存被填满时，它应该删除最近最少使用的项目。
 *
 * 它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 *
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。
 * 当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
 *
 * 示例:
 * LRUCache cache = new LRUCache( 2 ） 缓存容量
 *cache.put(1,1);
 *cache.put(2,2);
 *cache.get(1);       // 返回  1
 *cache.put(3,3);    // 该操作会使得密钥 2 作废
 *cache.get(2);       // 返回 -1 (未找到)
 *cache.put(4,4);    // 该操作会使得密钥 1 作废
 *cache.get(1);       // 返回 -1 (未找到)
 *cache.get(3);       // 返回  3
 *cache.get(4);       // 返回  4
 *
 * 思路：详见字节跳动包里的实现，这里只用linkedHashmap实现
 *
 */
public class Medium1625 {
	class LRUCache {
		private MyCache cache;

		public LRUCache(int capacity) {
			cache = new MyCache(16, 0.75f, true, capacity);
		}

		public int get(int key) {
			return cache.getOrDefault(key, -1);
		}

		public void put(int key, int value) {
			cache.put(key, value);
		}

		class MyCache extends LinkedHashMap<Integer, Integer> {
			private int limit;

			public MyCache(int initialCapacity, float loadFactor, boolean accessOrder, int limit) {
				super(initialCapacity, loadFactor, accessOrder);
				this.limit = limit;
			}

			@Override
			protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
				return super.size() > limit;
			}
		}
	}

	@Test
	public void test1() {
		LRUCache cache = new LRUCache(2);
		cache.put(1, 1);
		cache.put(2, 2);
		System.out.println(cache.get(1));
		cache.put(3, 3);
		System.out.println(cache.get(2));
		cache.put(4, 4);
		System.out.println(cache.get(1));
		System.out.println(cache.get(3));
		System.out.println(cache.get(4));
	}
}