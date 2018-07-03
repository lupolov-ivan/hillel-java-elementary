import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParserTxt {

    public static void main(String[] args) {
        ParserTxt pt = new ParserTxt();
        System.out.println(pt.parseTxt(new File("src/main/resources/students.txt")));
    }

    public List<Student> parseTxt(File in) {

        List<Student> studentList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(in))) {

            Iterator<String> iter = br.lines().iterator();

            while (iter.hasNext()) {

                String[] lastName = iter.next().split(" ");
                String[] firstName = iter.next().split(" ");
                String age[] = iter.next().split(" ");
                if(iter.hasNext()) iter.next();
                studentList.add(new Student(lastName[1],firstName[1],Integer.parseInt(age[1])));
            }

            writeToXml(studentList);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return studentList;
    }

    private void writeToXml(List<Student> list) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File("src/main/resources/students_out.xml")))) {

            String xmlBegin = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<students>\n";
            bw.write(xmlBegin);

            for (Student st: list) {

                bw.write("\t<student>\n" +
                        "\t\t<lastname>"+ st.lastname +"</lastname>\n" +
                        "\t\t<firstname>"+ st.firstname +"</firstname>\n" +
                        "\t\t<age>"+ st.age +"</age>\n" +
                        "\t</student>\n");
            }

            String xmlEnd = "</students>\n";
            bw.write(xmlEnd);

            bw.flush();

            } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
