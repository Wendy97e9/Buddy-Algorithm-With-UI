package buddy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;

import javax.swing.JTextArea;

public class Buddy {
	public static int memsize;	//内存空间的大小 MB
	public static int pagesize;//页框大小 KB
	private static int pagenum;	//页框数 = memsize/pagesize*2^10
	
//	private static Character[] Memory;
	
	//初始状态
	private static List<int[]> groupInitReq = new ArrayList<>();
	//保存分配的内存
	private static List<int[]> groupReq = new ArrayList<>();
	
	//10 个空闲块组
	private static List<int[]> group9 = new ArrayList<>();
	private static List<int[]> group8 = new ArrayList<>();
	private static List<int[]> group7 = new ArrayList<>();
	private static List<int[]> group6 = new ArrayList<>();
	private static List<int[]> group5 = new ArrayList<>();
	private static List<int[]> group4 = new ArrayList<>();
	private static List<int[]> group3 = new ArrayList<>();
	private static List<int[]> group2 = new ArrayList<>();
	private static List<int[]> group1 = new ArrayList<>();
	private static List<int[]> group0 = new ArrayList<>();
	private static Map<Integer, List> Mem = new HashMap<>();
	
	public static void init_mem(JTextArea textAreaOperationShow)
	{
		textAreaOperationShow.setText(null);
		textAreaOperationShow.append("...... ...... 初始化 ...... ......\n");
		group9.clear();
		group8.clear();
		group7.clear();
		group6.clear();
		group5.clear();
		group4.clear();
		group3.clear();
		group2.clear();
		group1.clear();
		group0.clear();
		groupInitReq.clear();
		groupReq.clear();
		Mem.clear();
		
		pagenum = memsize*((int)Math.pow(2, 10))/pagesize;
//		Memory = new Character[pagenum];
		System.out.println("页框数 : " + pagenum);
//		for(int i = 0; i < pagenum; i++)
//		{
//			Memory[i] = '_';
//		}
		int[] t;
		int group9size = (int)Math.pow(2,9);
		for(int i = 0; i < pagenum/group9size; i++)
		{
			//512
			t = new int[2];
			t[0] = i*group9size;
			t[1] = t[0]+group9size-1;
			group9.add(t);
		}
		Mem.put(9, group9);
		Mem.put(8, group8);
		Mem.put(7, group7);
		Mem.put(6, group6);
		Mem.put(5, group5);
		Mem.put(4, group4);
		Mem.put(3, group3);
		Mem.put(2, group2);
		Mem.put(1, group1);
		Mem.put(0, group0);		
	}
	
	public static void printGroups()
	{
		
		System.out.println("\nGroup 9 : ");
		for (int i = 0; i < group9.size(); i++)
		{
			int[] tem = group9.get(i);
			System.out.print("[ " + tem[0] + " -> " + tem[1] + " ] ");
		}
		System.out.println("\nGroup 8 : ");
		for (int i = 0; i < group8.size(); i++)
		{
			int[] tem = group8.get(i);
			System.out.print("[ " + tem[0] + " -> " + tem[1] + " ] ");
		}
		System.out.println("\nGroup 7 : ");
		for (int i = 0; i < group7.size(); i++)
		{
			int[] tem = group7.get(i);
			System.out.print("[ " + tem[0] + " -> " + tem[1] + " ] ");
		}
		System.out.println("\nGroup 6 : ");
		for (int i = 0; i < group6.size(); i++)
		{
			int[] tem = group6.get(i);
			System.out.print("[ " + tem[0] + " -> " + tem[1] + " ] ");
		}
		System.out.println("\nGroup 5 : ");
		for (int i = 0; i < group5.size(); i++)
		{
			int[] tem = group5.get(i);
			System.out.print("[ " + tem[0] + " -> " + tem[1] + " ] ");
		}
		System.out.println("\nGroup 4 : ");
		for (int i = 0; i < group4.size(); i++)
		{
			int[] tem = group4.get(i);
			System.out.print("[ " + tem[0] + " -> " + tem[1] + " ] ");
		}
		System.out.println("\nGroup 3 : ");
		for (int i = 0; i < group3.size(); i++)
		{
			int[] tem = group3.get(i);
			System.out.print("[ " + tem[0] + " -> " + tem[1] + " ] ");
		}
		System.out.println("\nGroup 2 : ");
		for (int i = 0; i < group2.size(); i++)
		{
			int[] tem = group2.get(i);
			System.out.print("[ " + tem[0] + " -> " + tem[1] + " ] ");
		}
		System.out.println("\nGroup 1 : ");
		for (int i = 0; i < group1.size(); i++)
		{
			int[] tem = group1.get(i);
			System.out.print("[ " + tem[0] + " -> " + tem[1] + " ] ");
		}
		System.out.println("\nGroup 0 : ");
		for (int i = 0; i < group0.size(); i++)
		{
			int[] tem = group0.get(i);
			System.out.print("[ " + tem[0] + " -> " + tem[1] + " ] ");
		}
	}
	
	//groupnum 0 1 .. 9 从Mem中获取
	public static void printSingleGroupUI(JTextArea textAreaFreeGroup, int groupnum)
	{
		List<int[]> sizex = Mem.get(groupnum);
		String showGroup = null;
		showGroup = "Group "+groupnum+" : \n";
		for (int i = 0; i < sizex.size(); i++)
		{
			int[] tem = sizex.get(i);
			showGroup = showGroup + "[ " + tem[0] + " -> " + tem[1] + " ] ";
		}
		showGroup = showGroup + "\n";
		textAreaFreeGroup.append(showGroup);
		showGroup = null;
	}
	
	public static void printGroupsUI(JTextArea textAreaFreeGroup)
	{
		textAreaFreeGroup.setText("");
		textAreaFreeGroup.append("当前空闲块组情况：\n");
		for (int i = 9; i >= 0; i--)
		{
			printSingleGroupUI(textAreaFreeGroup, i);
		}
	}
	
	public static int[] if_contain(int size, int begin, int end)
	{
		List<int[]> sizex = Mem.get(size);
		for (int i = 0; i < sizex.size(); i++)
		{
			int[] t = sizex.get(i);
			if (t[0] <= begin && t[1] >= end)
			{
				System.out.println("Find !");
				sizex.remove(i);
				Mem.put(size, sizex);
				return t;
			}
		}
		return null;
	}
	
	//1 2 4 .. 512
	public static boolean init_request_mem(JTextArea textAreaOperationShow, int beginaddr, int powsize)
	{
		
		if (beginaddr % powsize != 0)
		{
			textAreaOperationShow.append("输入不符合要求 : 起始地址应为连续页框数的倍数");
			return false;
		}
		int size = (int)(Math.log(powsize)/Math.log(2));	//计算组数
		if (powsize != Math.pow(2,size))
		{
			textAreaOperationShow.append("输入不符合要求 : 页框数应为1, 2, 4, .. , 512中的数字");
			return false;
		}
		List<int[]> sizex = Mem.get(size);
		int i = 0;
		int[] t ;
		for(i = 0; i < sizex.size(); i++)
		{
			
			t = sizex.get(i);
			if (t[0] == beginaddr && t[1] == beginaddr + powsize - 1)
			{
				groupInitReq.add(t);
				sizex.remove(i);
				textAreaOperationShow.append("初始分配成功!\n");
				
				return true;
			}
		}
		int find = -1;
		int[] findlist;
		int begin = -1;
		int end = -1;
		for (i = size; i < 10; i++)
		{
			//sizex = Mem.get(i);
			// 看是否包含，
			findlist = if_contain(i, beginaddr, beginaddr + powsize - 1);
			if (findlist != null)
			{
				find = i;
				begin = findlist[0];
				end = findlist[1];
//				System.out.println("--------------");
//				System.out.println(findlist[0]+findlist[1]);
				break;
			}
		}
		if (find == -1)
		{
			textAreaOperationShow.append("该内存区间已被初始化占用!不可重复分配!\n");
			return false;
		}

		int endaddr = beginaddr + powsize - 1;
		int mid = 0;
		while (find > size)
		{
			// 
			find--;
			int powfind = (int)Math.pow(2, find);
			mid = begin + powfind;	//第二段的开头坐标
			int[] t1;
			if(beginaddr >= begin && endaddr <= mid - 1)
			{
				t1 = new int[2];
				t1[0] = mid;
				t1[1] = end;
				end = mid - 1;	
			}
			else
			{
				t1 = new int[2];
				t1[0] = begin;
				t1[1] = mid - 1;
				begin = mid;

			}
			List<int[]> tem = Mem.get(find);
			tem.add(t1);
			Mem.put(find, tem);
		}
		if (begin == beginaddr && end == beginaddr + powsize - 1)
		{
			int[] t2 = new int[2];
			t2[0] = begin;
			t2[1] = end;
			
			groupInitReq.add(t2);
			
			textAreaOperationShow.append("初始分配成功!\n");
			
			return true;
		}
		return false;
		
//		//判断powsize是不是符合要求！！！
//		if (beginaddr % 512 != 0)
//		{
//			textAreaOperationShow.append("输入不符合要求 : 起始地址应为512的倍数");
//			return false;
//		}
//		int size = (int)(Math.log(powsize)/Math.log(2));
//		if (powsize != Math.pow(2,size))
//		{
//			textAreaOperationShow.append("输入不符合要求 : 页框数应为1, 2, 4, .. , 512中的数字");
//			return false;
//		}
//		Random random = new Random();
//		int n = random.nextInt(group9.size());
//		group9.get(n);
//		if (powsize == 512)
//		{
//			
//		}else
//		{
//			//
//		}
//		return true;
		
	}
	
	//从空闲列表size得到一个块 0 1 2 3 .. 9
	static int[] get_from_list(int size)
	{
		int[] rt = new int[2];
		List<int[]> sizex = Mem.get(size);
		if (sizex.size() > 0)
		{
			rt = sizex.get(0);
			sizex.remove(0);
			Mem.put(size, sizex);
		}
		return rt;
	}
	
	static boolean break_list(int size)
	{
		if (size > 9)
			return false;
		List<int[]> sizex = Mem.get(size);
		boolean rt = true;
		if (sizex.size() == 0)
		{
			rt = false;
			rt = break_list(size + 1);
		}
		if (rt)
		{
			sizex = Mem.get(size);
			int[] t = sizex.get(0);
			sizex.remove(0);
			Mem.put(size, sizex);
			
			int[] t1 = new int[2];
			t1[0] = t[0];
			t1[1] = t1[0] + (int)Math.pow(2, size - 1) - 1;
			t[0] = t1[1] + 1;
			
			List<int[]> tem = Mem.get(size-1);
			tem.add(t1);
			tem.add(t);
			Mem.put(size-1, tem);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//请求分配实际大小的页数
	public static boolean request_mem(JTextArea textAreaOperationShow, int realsize)
	{
		boolean rt;
		if (realsize > 512)
		{
			textAreaOperationShow.append("分配失败 : 系统设置 : 最大分配连续的页面数为512!\n");
			return false;
		}
		//56->64 127->128
		//t 0 1 .. 9
		int t = (int) Math.ceil(Math.log(realsize)/Math.log(2));
		System.out.println("t = " + t);
		List<int[]> tem = Mem.get(t);
		if(tem.size() == 0)
		{
			rt = break_list(t + 1);
			if (!rt)
			{
				textAreaOperationShow.append("分配失败 : 内存不足, 请先释放!\n");
				return false;
			}
		}
		int[] get = get_from_list(t);
		groupReq.add(get);
		String showOperation = null;
		showOperation = "分配得到内存 [ "+get[0]+" -> "+get[1] + " ] 块组号是 " + t + "\n";
		System.out.println(showOperation);
		textAreaOperationShow.append(showOperation);
		
//		printGroups();
		return true;
	}
	
	public static int[] get_neighbor(int size, int begin, int end)
	{
		if (size >= 9)
			return null;
		List<int[]> tem = Mem.get(size);
		System.out.println("get_neighbor groupnum "+size);
		for (int i = 0; i < tem.size(); i++)
		{
			int[] t = tem.get(i);
			if (t[0] == end + 1 || t[1] == begin - 1)
			{
				tem.remove(i);
				Mem.put(size, tem);
				return t;
			}
			
		}
		return null;
	}
	
	// size 0 1 .. 9
	public static int[] combine_list(int size, int begin, int end)
	{
		int[] rt = {begin, end};
		System.out.println("combine_list groupnum "+size);
		while(true)
		{
			
			int[] neighbor = get_neighbor(size, begin, end);
			if (neighbor == null)
			{
				return rt;
			}
			else 
			{
				if (neighbor[0] > rt[1])
				{
					rt[1] = neighbor[1];
				}
				else
				{
					rt[0] = neighbor[0];
				}
				begin = rt[0];
				end = rt[1];
				size = size + 1;
			}
		}
	}
	
	//begin 起始地址， groupnum 块组号
	public static boolean release_mem(JTextArea textAreaOperationShow, int begin, int groupnum)
	{
		//System.out.println(groupReq.size());
		String showOperation = null;
		int end = begin + (int)Math.pow(2, groupnum) - 1;
		int[] rel = new int[2];
		rel[0] = begin;
		rel[1] = end;
		System.out.println("释放 "+rel[0]+ rel[1]);
		showOperation = "释放 [ " + rel[0] + " -> " + rel[1] + " ]";
		boolean flag = false;
		int relix = -1;
		for(int i = 0; i < groupReq.size(); i++)
		{
			int[] t = groupReq.get(i);
			System.out.println(t[0] +" "+t[1]);
			if (t[0] == begin && t[1] == end)
			{
				flag = true;
				relix = i;
				System.out.println("曾经分配");
			}
		}
		if (flag == false)
		{
			System.out.println("释放失败：释放未申请的内存！");
			return false;
		}
		groupReq.remove(relix);
		showOperation = showOperation + " 成功\n";
		System.out.println("release_mem groupnum "+groupnum);
		int[] mem_after_combine = combine_list(groupnum, begin, end);
		groupnum = (int)(Math.log(mem_after_combine[1] - mem_after_combine[0] + 1)/Math.log(2));
		System.out.println("合并之后的块组号 "+ groupnum);
		List<int[]> tem = Mem.get(groupnum);
		tem.add(mem_after_combine);
		Mem.put(groupnum, tem);
		textAreaOperationShow.append(showOperation);
		return true;
	}
	
	
}
