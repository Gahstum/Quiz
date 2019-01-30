# Quiz  von Burgert (Gahstum) und Stellmacher (Xarloran)


private Boolean checkYesNo(String input)
    {
        Boolean yes = false;
        while (input.isBlank())
        {
            System.out.println("Type 'yes' to continue or 'no'.");
            userAnswer = scanner.nextLine();
            checkYesNo(userAnswer);
        }
        if (input.equalsIgnoreCase("yes"))
        {
            yes = true;
        } else if ( input.equalsIgnoreCase("no"))
        {
            yes = false;
        }
        return yes;
    }
}
