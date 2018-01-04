package ustc.lzj;


import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;

public class  PasswdMap{
    private  HashMap<String,String> hashMap;
    private String filePath;

    public PasswdMap(HttpServletRequest req) throws IOException {
        filePath = req.getServletContext().getRealPath("/WEB-INF/userInfo.dat");
        FileInputStream fiStream = null;
        ObjectInputStream oiStream = null;
        try {
            fiStream = new FileInputStream(filePath);
            oiStream = new ObjectInputStream(fiStream);
            HashMap serializeResult = (HashMap) oiStream.readObject();
            System.out.println(" 序列化结果为：：" + serializeResult);
            hashMap=serializeResult;
        } catch (Exception ex) {
            hashMap=new HashMap();
            hashMap.put("lzj","123");
        } finally {
            if (fiStream != null)
                fiStream.close();
            if (oiStream != null) {
                oiStream.close();
            }
        }
    }

    private void writeFile()  {
        FileOutputStream fos = null;
        ObjectOutputStream ooS = null;
        try {
            fos = new FileOutputStream(filePath);
            ooS = new ObjectOutputStream(fos);
            ooS.writeObject(hashMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fos != null){
                try{
                    fos.close();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
            if (ooS != null) {
                try{
                    ooS.close();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }


    public void addUser(String user, String key){
        hashMap.put(user,key);
        writeFile();
    }

    public void deleteUser(String user){
        hashMap.remove(user);
        writeFile();
    }

    public HashMap<String, String> getHashMap() {
        return hashMap;
    }

    public String getPasswd(String user){
        return hashMap.get(user);
    }

}
