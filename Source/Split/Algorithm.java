/*//////////////////////////////////////
//       AML account generator        //
//      Author: VolodyaHoi (At0m)     //
//	   Team: Atomic Threat Team       //
//////////////////////////////////////*/

public class Algorithm {

    public static void main(String args[]) {

		// get random A-Z

		String[] alfabetLargeMassive = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
										"s", "t", "u", "v", "x", "y", "z"};

		String[] alfabetStrongMassive = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
										"S", "T", "U", "V", "X", "Y", "Z"};
        
        // get random numbers

        int fNumberRandom;
        int sNumberRandom;
        int tNumberRandom;
        
        int[] numberMassive = new int[3];

        for ( int i = 0; i < 3; i++) 
        {
            numberMassive[i] = 0;
        }

        int allNumberRandom = 0 + (int) (Math.random() * 3);

        int firstAnrNumber;

		String password = ""; 

        do 
        {
            fNumberRandom = 1 + (int) (Math.random() * 9);
        } while ( fNumberRandom > 9 );

        numberMassive[allNumberRandom] = fNumberRandom;

        if ( allNumberRandom == 0 )
        {
            do 
            {
                sNumberRandom = 10 + (int) (Math.random() * 99);
            } while (sNumberRandom > 99);

            numberMassive[1 + (int) (Math.random() * 2)] = sNumberRandom;
            for ( int i = 0; i < 3; i++) 
            {
                if ( numberMassive[i] == 0 ) 
                {
                    do 
                    {
                        tNumberRandom = 100 + (int) (Math.random() * 999);
                    } while (tNumberRandom > 999);

                    numberMassive[i] = tNumberRandom;
                }
            }
        }
        else if ( allNumberRandom == 1 ) 
        {
            do {
                firstAnrNumber = 0 + (int) (Math.random() * 2);
                if ( firstAnrNumber != 1 )
                {
                    break;
                }
            } while (true);

            do 
            {
                sNumberRandom = 10 + (int) (Math.random() * 99);
            } while (sNumberRandom > 99);

            numberMassive[firstAnrNumber] = sNumberRandom;
            for ( int i = 0; i < 3; i++) 
            {
                if ( numberMassive[i] == 0 ) 
                {
                    do 
                    {
                        tNumberRandom = 100 + (int) (Math.random() * 999);
                    } while (tNumberRandom > 999);

                    numberMassive[i] = tNumberRandom;
                }
            }
        }
        else if ( allNumberRandom == 2 ) 
        {
            firstAnrNumber = 0 + (int) (Math.random() * 1);
            do 
            {
                sNumberRandom = 10 + (int) (Math.random() * 99);
            } while (sNumberRandom > 99);
            numberMassive[firstAnrNumber] = sNumberRandom;

            for ( int i = 0; i < 3; i++) 
            {
                if ( numberMassive[i] == 0 ) 
                {
                    do 
                    {
                        tNumberRandom = 100 + (int) (Math.random() * 999);
                    } while (tNumberRandom > 999);
                    numberMassive[i] = tNumberRandom;
                }
            }
        }
                        

        System.out.println("Algorithm started.");    

        // HARD PASSWORD

        for ( int i = 0; i < 3; i++ ) 
        {

			int alfabetLargeNumber = 0 + (int) (Math.random() * 24);
			int alfabetStrongNumber = 0 + (int) (Math.random() * 24);
			
			int[] alfabetGotValueMassive = {alfabetLargeNumber, alfabetStrongNumber};

			int cubeOfYourLife = 0 + (int) (Math.random() * 5);
			int cubeLargeFriend = 0 + (int) (Math.random() * 1);
			int cubeStrongFriend = 0 + (int) (Math.random() * 1);

			if ( cubeOfYourLife == 0 )
			{
				password = password + numberMassive[i] + alfabetLargeMassive[alfabetLargeNumber] + alfabetStrongMassive[alfabetStrongNumber];
			}
			else if ( cubeOfYourLife == 1 ) 
			{
				password = password + numberMassive[i] + alfabetStrongMassive[alfabetStrongNumber] + alfabetLargeMassive[alfabetLargeNumber];
			}
			else if ( cubeOfYourLife == 2 )
			{
				password = numberMassive[i] + password + alfabetStrongMassive[alfabetStrongNumber] + alfabetLargeMassive[alfabetLargeNumber];
			}
			else if ( cubeOfYourLife == 3 )
			{
				password = alfabetStrongMassive[alfabetStrongNumber] + password + numberMassive[i]+ alfabetLargeMassive[alfabetLargeNumber];
			}
			else if ( cubeOfYourLife == 4 )
			{
				password = alfabetStrongMassive[alfabetStrongNumber] + numberMassive[i] + alfabetLargeMassive[alfabetLargeNumber] + password;
			}
			else if ( cubeOfYourLife == 5 )
			{
				password =  alfabetLargeMassive[alfabetLargeNumber] + alfabetStrongMassive[alfabetStrongNumber] + numberMassive[i] + password;
			}



        }


        System.out.println("Hard Password: " + password);   

        // NORNAL PASSWORD 

        password = "";

        for (int i = 0; i < 2; i++){
            int alfabetLargeNumber = 0 + (int) (Math.random() * 24);
		    int alfabetStrongNumber = 0 + (int) (Math.random() * 24);
            int doubleNumber;
            do 
            {
                doubleNumber = 10 + (int) (Math.random() * 99);
            } while (doubleNumber > 99);

            password = password + alfabetStrongMassive[alfabetStrongNumber] + alfabetLargeMassive[alfabetLargeNumber] + doubleNumber;
        }

        int alfabetLargeNumber = 0 + (int) (Math.random() * 24);
		int alfabetStrongNumber = 0 + (int) (Math.random() * 24);

        password += alfabetStrongMassive[alfabetStrongNumber] + alfabetLargeMassive[alfabetLargeNumber];

        alfabetStrongNumber = 0 + (int) (Math.random() * 24);
        password += alfabetStrongMassive[alfabetStrongNumber];

        System.out.println("Normal Password: " + password); 
        
        // EAZY PASSWORD 

        password = "";

        int threeNumber;

        for (int i = 0; i < 3; i++) {
            alfabetLargeNumber = 0 + (int) (Math.random() * 24);
		    alfabetStrongNumber = 0 + (int) (Math.random() * 24);
            password += alfabetStrongMassive[alfabetStrongNumber] + alfabetLargeMassive[alfabetLargeNumber];
        }
        do 
        {
            threeNumber = 100 + (int) (Math.random() * 999);
        } while (threeNumber > 999);

        password += threeNumber;

        alfabetLargeNumber = 0 + (int) (Math.random() * 24);
        password += alfabetLargeMassive[alfabetLargeNumber];

        System.out.println("Eazy password: " + password);

		System.out.println("Algorithm finished.");  
    }
}
