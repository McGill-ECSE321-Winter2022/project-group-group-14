package ca.mcgill.ecse321.grocerystore.model;


import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;



@MappedSuperclass
public abstract class AccountType
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //AccountType Associations
  private Account account;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public AccountType(Account aAccount)
  {
    boolean didAddAccount = setAccount(aAccount);
    if (!didAddAccount)
    {
      throw new RuntimeException("Unable to create accountType due to account. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  @OneToOne
  public Account getAccount()
  {
    return account;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setAccount(Account aNewAccount)
  {
    boolean wasSet = false;
    if (aNewAccount == null)
    {
      //Unable to setAccount to null, as accountType must always be associated to a account
      return wasSet;
    }
    
    AccountType existingAccountType = aNewAccount.getAccountType();
    if (existingAccountType != null && !equals(existingAccountType))
    {
      //Unable to setAccount, the current account already has a accountType, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Account anOldAccount = account;
    account = aNewAccount;
    account.setAccountType(this);

    if (anOldAccount != null)
    {
      anOldAccount.setAccountType(null);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Account existingAccount = account;
    account = null;
    if (existingAccount != null)
    {
      existingAccount.setAccountType(null);
    }
  }

}