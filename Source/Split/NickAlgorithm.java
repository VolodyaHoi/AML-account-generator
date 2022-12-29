/*//////////////////////////////////////
//       AML account generator        //
//      Author: VolodyaHoi (At0m)     //
//	   Team: Atomic Threat Team       //
//////////////////////////////////////*/

public class NickAlgorithm {

    public static void main(String args[]) {

        String nickName = "";

        String[] alfabetLargeMassive = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
										"s", "t", "u", "v", "x", "y", "z"};

		String[] alfabetStrongMassive = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
										"S", "T", "U", "V", "X", "Y", "Z"};
        
        int nickTagNumber;
        do
        {
            nickTagNumber = 1000 + (int) (Math.random() * 9999);
            
        } while (nickTagNumber > 9999);

        int nickNameRandom;

        for ( int i = 0; i < 3; i++) 
        {
            nickNameRandom = 0 + (int) (Math.random() * 5);

            if (nickNameRandom == 0) 
            {
                nickName = nickName + alfabetLargeMassive[0 + (int) (Math.random() * 24)] + alfabetStrongMassive[0 + (int) (Math.random() * 24)];
                System.out.println(nickName); 
            }
            else if (nickNameRandom == 1) 
            {
                nickName = nickName + alfabetStrongMassive[0 + (int) (Math.random() * 24)] + alfabetLargeMassive[0 + (int) (Math.random() * 24)];
                System.out.println(nickName); 
            }
            else if (nickNameRandom == 2)
            {
                nickName = alfabetLargeMassive[0 + (int) (Math.random() * 24)] + nickName + alfabetStrongMassive[0 + (int) (Math.random() * 24)];
                System.out.println(nickName);
            }
            else if (nickNameRandom == 3)
            {
                nickName = alfabetLargeMassive[0 + (int) (Math.random() * 24)] + alfabetStrongMassive[0 + (int) (Math.random() * 24)]  + nickName;
                System.out.println(nickName); 
            }
            else if (nickNameRandom == 4)
            {
                nickName = alfabetStrongMassive[0 + (int) (Math.random() * 24)] + alfabetLargeMassive[0 + (int) (Math.random() * 24)] + nickName;
                System.out.println(nickName);
            }
            else if (nickNameRandom == 5)
            {
                nickName = alfabetStrongMassive[0 + (int) (Math.random() * 24)] + nickName + alfabetLargeMassive[0 + (int) (Math.random() * 24)];
                System.out.println(nickName);
            }
        }

        nickName = nickName + nickTagNumber;
        System.out.println(nickName);
    }
}
