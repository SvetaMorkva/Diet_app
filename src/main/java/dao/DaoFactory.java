package dao;

public class DaoFactory {
    private static DaoFactory daoFactory;

    public UserDAO createUserDao() { return new UserDAO(); }
    public FoodTypeDAO createFoodTypeDao() { return new FoodTypeDAO(); }
    public User_FoodTypeDAO createUserFoodTypeDao() { return new User_FoodTypeDAO(); }

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DaoFactory();
        }
        return daoFactory;
    }
}
