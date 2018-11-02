public class p1{
	public static double getmedia(int[] nums1,int[] nums2){
	int[] nums=new int[nums1.length+nums2.length];
	int i=0,j=0;
	double result=0;
	while((i+j)<nums.length){
	if(i<nums1.length&&j<nums2.length){
		if(nums1[i]<nums2[j]){
			nums[i+j]=nums1[i];
			i++;
		}
		else{
			nums[i+j]=nums2[j];
			j++;
		}
	}
	else if(i<nums1.length){
		nums[i+j]=nums1[i];
		i++;
	}
	else{
		nums[i+j]=nums2[j];
		j++;
	}
	}
	if(nums.length%2==0){
		result=(nums[nums.length/2]+nums[nums.length/2-1])/2.0;
	}
	else{
		result=nums[nums.length/2];
	}
	return result;
	}
	public static void main(String[] args){
		int[] l1={1,3,4,5};
		int[] l2={2,3,4};
		System.out.println(getmedia(l1,l2));
<<<<<<< HEAD
		System.out.println('editbymac');
=======
		System.out.println('edit by windows');
>>>>>>> 5b9e83453353f516938ffe51715d1dfb7d447717
	}
}