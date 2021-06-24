package Array.Plan6;

/**linear scan:
 * check each idx one by one to see if the idx could place flower or not
 * then we record the #of idx which could place flower (totalNum)
 * if (n <= totalNum) ->means al required flower could be placed, return true;
 *
 * how to che each idx to decide if it can place flower:
 * 分成四种case：
 * need to check left ( i > 0)
 * check right ( i < flowerbed.length - 1)
 * check left and right i > 0 && i < flowerbed.length - 1
 * check neither left nor right (i == 0)
 * idx所在的位置一定会落入上面四种case之一，再在该case下进行检查即可
 *
 * TC:O(N)
 * SC:O(1)
 * */
public class CanPlaceFlowers {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int totlNum = 0;  //#of idx which could still place flower in whole array

        for (int i = 0; i < flowerbed.length; i++) {

            if(flowerbed[i] != 0) {
                continue;
            }

            boolean checkLeft = i > 0;
            boolean checkRight = i < flowerbed.length - 1;

            boolean canPlace = false;

            if(checkLeft && checkRight) {
                canPlace = flowerbed[i + 1] == 0 && flowerbed[i - 1] == 0;
            }
            else if(checkLeft && !checkRight) {
                canPlace = flowerbed[i - 1] == 0;
            }
            else if(!checkLeft && checkRight) {
                canPlace = flowerbed[i + 1] == 0;
            }
            else{
                canPlace = true; // Single element in array. Nothing to check
            }

            if (canPlace) {
                totlNum++;
                flowerbed[i] = 1;  //Important - set a valid place as 1
            }
        }

        return n <= totlNum;
    }
}
