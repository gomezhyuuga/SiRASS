/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyforge.sirass.security;

import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Eder Herget
 */
public class Signature {
    private Object obj;
    private String rutaRaiz;
    private String rutaSignRaiz;
    private String namePrivateKey;
    private String namePublicKey;
    private PrivateKey privateKey;
    private PublicKey publicKey;
    private byte[] objFirmado;
    private Cipher cipher;
    private PrivateKey rsaPrivateKey;
    private PublicKey rsaPublicKey;
    private KeyFactory keyFactory;
    private java.security.Signature jSignature;
    
    public Signature(String rutaRaiz) {
        try {
            RSAPublicKeySpec rsaPublicKeySpect=new RSAPublicKeySpec(new BigInteger("109724321734783809719337113802270955062579502144045010633709271060621631791019157836784572625290761665025738576084438622346190714296752797015010513662595047802503086846937140645648800824895251069765361684693314678400734443758107424830855473037253148158115834041252535747547634199754324365059529494394595281126085342116344105142512569426611149972158925077909204469442206181173881524238309138766707747481696992887943743001959702346410922338196308567521868161835974966688124685669563989780096577434855330570066569630597454523353149111597059246943614767178342967486109121893954438072530720729167946526695734138735616946312143756101831106494902389010188630734571520049548691660772767294671883115503918805231358758156294498136227776330231041127247974403948142512346427547053225401371538433229801646141686435214274538019193657027345461672285999597423317869963295348189986065291070195975604871782624358196461824309919262606525683466428536493126479658574134770137640397119450484125372710883458879807216997295491908831923255834173794575815854882703398396106254394221879195782563887478358500650092360457752540010709140707199105800685127534798959148216348159247482494355237943989197921646084041918335364704447998415232725135935144578074394330632649997834570411285112938146332071202749558232487647966390186781890189727156266318115920121722825618686644891975647916842285842992628678278994244870265402918608018606858264810299422498686383107846435255472898666424163761697367630435308741743933507612510327544656253777482217395078467870828634299641716702440329"), new BigInteger("65537"));
            RSAPrivateKeySpec rsaPrivateKeySpec=new RSAPrivateKeySpec(new BigInteger("109724321734783809719337113802270955062579502144045010633709271060621631791019157836784572625290761665025738576084438622346190714296752797015010513662595047802503086846937140645648800824895251069765361684693314678400734443758107424830855473037253148158115834041252535747547634199754324365059529494394595281126085342116344105142512569426611149972158925077909204469442206181173881524238309138766707747481696992887943743001959702346410922338196308567521868161835974966688124685669563989780096577434855330570066569630597454523353149111597059246943614767178342967486109121893954438072530720729167946526695734138735616946312143756101831106494902389010188630734571520049548691660772767294671883115503918805231358758156294498136227776330231041127247974403948142512346427547053225401371538433229801646141686435214274538019193657027345461672285999597423317869963295348189986065291070195975604871782624358196461824309919262606525683466428536493126479658574134770137640397119450484125372710883458879807216997295491908831923255834173794575815854882703398396106254394221879195782563887478358500650092360457752540010709140707199105800685127534798959148216348159247482494355237943989197921646084041918335364704447998415232725135935144578074394330632649997834570411285112938146332071202749558232487647966390186781890189727156266318115920121722825618686644891975647916842285842992628678278994244870265402918608018606858264810299422498686383107846435255472898666424163761697367630435308741743933507612510327544656253777482217395078467870828634299641716702440329"), new BigInteger("78280519203374457287293072507728165386056230865724224746169502383545554664096491200645428958727052694049825790979202774408631659027098795752534165079394754948408293461943527290354385024776879610265182277637374629648972941275219658443192067035869939046353417709580901802223739027476283473651881548436969909582850088591052153440702468775052732473232871522082529932301444866365045768760950029634804575144639281619065530124961896988095107875623031316402222679933516113683414831364971449809393099692450002840136602677086448627399786988446711051010813007220205437962990648080835767681145709727527602847279944846281070325797192325866262038471026383578137229635558936042795651727895562928264622533050051538175342327179390352851938079406995781908625757834978704415937097614935388797186088858801394231460918095505453219008332653111702033441485067170450012929883755697001344739872843906620632871378891947736412709382316355946314578110311336124996879024631007512545393466946215342368084687509770924722725983730529330237402883918996720980825214329744770499585152502340407928645377996622917541875030463901091446978195615366040851561047041483740257606454413965893816375846690317121410937613486856824893097741187139152894054001440871324083208007810435239986898070205774100570187886995706896405696668339215209383173052592008998317712407925764634188221392397727804716779428481904573202111635297543692564705236868784619552298061921570876488996917526768511703872511932864146411306830351247155444992171313675822162375185248778996533997958667053763730989408495313"));
            keyFactory= KeyFactory.getInstance("RSA");
            rsaPrivateKey=keyFactory.generatePrivate(rsaPrivateKeySpec);
            rsaPublicKey=keyFactory.generatePublic(rsaPublicKeySpect);
            this.rutaRaiz=rutaRaiz;
            this.rutaSignRaiz=rutaRaiz.concat("/Seguridad/Signature/");
            namePrivateKey="Private.key";
            namePublicKey="Public.key";
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(Signature.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Signature.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void sendObject(Object obj){
        this.obj=obj;
    }
    
    
    public boolean isAuthentic(String firmaD){
        boolean isAuthenticate=false;
        try {
            loadPublicKey();
            jSignature= java.security.Signature.getInstance("SHA1withDSA", "SUN");
            jSignature.initVerify(publicKey);
            jSignature.update(objectToByte(obj));
            isAuthenticate=jSignature.verify(legibleStringToByte(firmaD));
        } catch (SignatureException ex) {
            Logger.getLogger(Signature.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Signature.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Signature.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(Signature.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isAuthenticate;
    }
    
    
    public String getStringSign(){
        String sObjFirmado="";
        try {
            loadPrivateKey();
            jSignature = java.security.Signature.getInstance("SHA1withDSA", "SUN");
            jSignature.initSign(privateKey);
            jSignature.update(objectToByte(obj));
            objFirmado=jSignature.sign();
            sObjFirmado=byteToLegibleSring(objFirmado);
        } catch (SignatureException ex) {
            Logger.getLogger(Signature.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Signature.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Signature.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(Signature.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sObjFirmado;
    }
    
   private static Object byteToObject(byte[] abyte){
       Object obj=null; 
       try {
            obj=new ObjectInputStream(new ByteArrayInputStream(abyte)).readObject();
        } catch (IOException ex) {
            System.err.println("Error detectado: "+ex.getLocalizedMessage()+"  Mensage: "+ex.getMessage());
            Logger.getLogger(Signature.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            System.err.println("Error detectado: "+ex.getLocalizedMessage()+"  Mensage: "+ex.getMessage());
            Logger.getLogger(Signature.class.getName()).log(Level.SEVERE, null, ex);
        }
       return obj;
   }
   
    
    private static byte[] objectToByte(Object obj){
        ByteArrayOutputStream baos=null;
        try {
            baos=new ByteArrayOutputStream();
            new ObjectOutputStream(baos).writeObject(obj);
        } catch (IOException ex) {
            
            Logger.getLogger(Signature.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return baos.toByteArray();
    }
    
    public void generateKeys(){
        try {
                FileOutputStream fileOutputStream;
                cipher= Cipher.getInstance("RSA");
                cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);
                
                KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("DSA", "SUN");
                SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
                keyGenerator.initialize(1024, random);
                KeyPair keyPair=keyGenerator.generateKeyPair();
                fileOutputStream=new FileOutputStream(new File(rutaSignRaiz.concat(namePrivateKey)));
                fileOutputStream.write(cipher.doFinal(objectToByte(keyPair.getPrivate())));
                fileOutputStream.flush();
                fileOutputStream.close();
                fileOutputStream=new FileOutputStream(new File(rutaSignRaiz.concat(namePublicKey)));
                fileOutputStream.write(cipher.doFinal(keyPair.getPublic().getEncoded()));
                fileOutputStream.flush();
                fileOutputStream.close();
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(Signature.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(Signature.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Signature.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(Signature.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Signature.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Signature.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(Signature.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadPublicKey(){
        loadKey(namePublicKey);
    }
    
    private void loadPrivateKey(){
        loadKey(namePrivateKey);
    }
    
    private void loadKey(String keyType){
        try {
            FileInputStream fileInputStream;
            byte[] keyReaded;
            byte[] decryptKey;
            cipher= Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);
            
            if(keyType.equals(namePublicKey)){
                fileInputStream=new FileInputStream(new File(rutaSignRaiz.concat(namePublicKey)));
            }else{
                fileInputStream=new FileInputStream(new File(rutaSignRaiz.concat(namePrivateKey)));    
            }
            keyReaded=new byte[fileInputStream.available()];
            fileInputStream.read(keyReaded);
            fileInputStream.close();
            decryptKey=cipher.doFinal(keyReaded);
            X509EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(decryptKey);

            keyFactory = KeyFactory.getInstance("DSA", "SUN");
            if(keyType.equals(namePublicKey)){
                publicKey=keyFactory.generatePublic(encodedKeySpec);
            }else{
            privateKey=(PrivateKey)byteToObject(decryptKey);
            }
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(Signature.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(Signature.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Signature.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(Signature.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(Signature.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Signature.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(Signature.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Signature.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    
    private static String byteToLegibleSring(byte[] Abyte){
         String sb="";
        for(byte ab:Abyte){
            sb= sb.concat(".").concat(String.valueOf(ab)) ;
        }
        sb=sb.substring(1);
        return sb;
    }
    private static byte[] legibleStringToByte(String textoCnByte){
        byte[] Arreglo;
        String textoAcum;
        int tArray;
        int iArray;
        textoAcum="";
        tArray=1;
        iArray=0;
        for(int j=0;j<textoCnByte.length();j++){
            if(textoCnByte.charAt(j)=='.'){
                tArray++;
            }
        }
        Arreglo=new byte[tArray];
        for(int i=0; i<textoCnByte.length(); i++){
            if(textoCnByte.charAt(i)!='.'){
                textoAcum=textoAcum.concat(String.valueOf(textoCnByte.charAt(i)));
            }else{
                Arreglo[iArray]=(byte)Integer.parseInt(textoAcum);
                iArray++;
                textoAcum="";
            }
        }
        if(tArray>1){
          Arreglo[iArray]=(byte)Integer.parseInt(textoAcum);  
        }
        return Arreglo;
    }
    
    
}
