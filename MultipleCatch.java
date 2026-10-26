
public class MultipleCatch {
    public static void main(String[] args) {
        try {
            int[] numbers = {10, 20, 30};

            int result = 10 / 0;
            System.out.println(numbers[5]);
            System.out.println(result);

        } catch (ArithmeticException e) {
            System.out.println("Error: Division by zero!");

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Invalid array index!");

        } catch (Exception e) {
            System.out.println("Some other error occurred.");
        }
    }
}

