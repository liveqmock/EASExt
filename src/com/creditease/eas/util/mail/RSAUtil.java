package com.creditease.eas.util.mail;
/**
 * <p>Title:RSA算法 </p>
 *
 * <p>Description:简单实现RSA算法、以及利用RSA算法实现加密、解密、签名、验证 </p>
 *
 * <p>Copyright: Copyright (c) 2008/5/27</p>
 *
 * <p>Company: 建工学院</p>
 *
 * @author 04信息（1）程晟
 * @version 1.0
 */
import java.math.BigInteger;
public class RSAUtil {
    private long n = 405835094141L;
    private long m = 405833817984L;
    private long public_key = 98629; //公匙
    private long private_key = 381145309453L; //密匙
    private long secretword = 0; //密文
    private long word = 0; //解密后明文
    //判断是否为素数
    public boolean primenumber(long t) {
        long k = 0;
        k = (long) Math.sqrt((double) t);
        boolean flag = true;
        outer:for (int i = 2; i <= k; i++) {
            if ((t % i) == 0) {
                flag = false;
                break outer;
            }
        }
        return flag;
    }
    //随机产生大素数（1e6数量级，注意，太大了要超出范围）
//    public void bigprimeRandom() {
//        do{
//            p = (long) (Math.random() * 1000000);
//        }while(!this.primenumber(p));
//        do{
//            q=(long)(Math.random()*1000000);
//        }while(p==q||!this.primenumber(q));
//        System.out.println("p\t" + p + "\tq" + q);
//    }
    //输入PQ
//    public void inputPQ() throws Exception {
//        this.bigprimeRandom();
//        System.out.println("自动生成两个大素数p,q分别为:" + this.p+" "+this.q);
//        this.n = (long)p*q;
//        this.m = (long)(p - 1) * (q - 1);
//
//        System.out.println("这两个素数的乘积为p*q：" + this.n);
//        System.out.println("所得的m=(p-1)(q-1)：" + this.m);
//    }
    //求最大公约数
    public long gcd(long a, long b) {
        long gcd;
        if (b == 0)
            gcd = a;
        else{
            gcd = gcd(b, a % b);
        }
        return gcd;
    }
    //生成公匙
    public void getPublic_key() throws Exception {
        do {
            this.public_key=(long)(Math.random()*100000);
        } while ((this.public_key >= this.m) ||
                 (this.gcd(this.m, this.public_key) != 1));
        System.out.println("生成的公钥为：" + this.public_key);
    }

    //计算得到密匙
    public void getPrivate_key() {
        long value = 1;
        outer:for (long i = 1; ; i++) {
            value = i * this.m + 1;
            //System.out.println("value:    " + value);
            if ((value % this.public_key == 0) &&
                (value / this.public_key < this.m)) {
                this.private_key = value / this.public_key;
                break outer;
            }
        }
        System.out.println("产生的一个私钥为：" + this.private_key);
    }
    //加密、解密计算
    public long colum(long y, long n, long key) {
        BigInteger bigy=new BigInteger(String.valueOf(y));
        //System.out.println(bigy);
        BigInteger bign=new BigInteger(String.valueOf(n));
        BigInteger bigkey=new BigInteger(String.valueOf(key));
        //System.out.println(Long.parseLong(bigy.modPow(bigkey,bign).toString()));
        return Long.parseLong(bigy.modPow(bigkey,bign).toString());
    }
    /***
     * 描述：加密
     * 2013-6-12 上午09:58:41 by ygq
     * @version
     * @throws Exception
     */
    public String encode(String text) throws Exception {
    	StringBuffer  sb = new StringBuffer();
        for (int i = 0; i < text.length(); i++) {
            this.secretword = this.colum( (long)text.charAt(i), this.n,
                                         this.public_key);
            if(i==text.length()-1){
            	sb.append(secretword);
            }else{
            	sb.append(secretword + "_");
            }
        }
        return sb.toString();
    }
    /**
     * 描述：解密
     * 2013-6-12 上午10:36:31 by ygq
     * @version
     * @param str
     * @return
     */
    public String decode(String str){
    	//解密
        StringBuffer destr=new StringBuffer();
    	String[] arrs = str.split("_ ");
    	for(int i=0;i<arrs.length;i++){
    		System.out.println(arrs[i]);
    		this.word = this.colum(Long.parseLong(arrs[i]), this.n, this.private_key);
          destr.append((char)word);
        }
        return destr.toString();
    }
    /**
     * 描述：加密 --- 带签名
     * 2013-6-12 上午10:36:22 by ygq
     * @version
     * @param text
     * @return
     * @throws Exception
     */
    public String encodeSignautre(String text) throws Exception {
    	if(null == text || "".equals(text)){
		   return "";
	    }
    	StringBuffer  sb = new StringBuffer();
        for (int i = 0; i < text.length(); i++) {
            this.secretword = this.colum((long)text.charAt(i), this.n,
                                         this.private_key);//签名后使用的是私密
            if(i==text.length()-1){
            	sb.append(secretword);
            }else{
            	sb.append(secretword + "_");
            }
        }
        return sb.toString();
    }
    /**
     * 描述：解密---带签名
     * 2013-6-12 上午10:36:06 by ygq
     * @version
     * @param text
     * @return
     */
    public  String decodeSignautre(String text){
    	 //验证
        StringBuffer destr=new StringBuffer();
    	String[] arrs = text.split("_");
        for(int j=0;j<arrs.length;j++) {
        	this.word = this.colum(Long.parseLong(arrs[j].toString()), this.n, this.public_key);
          destr.append((char)word);
        }
        return destr.toString();
    }
    public static void main(String[] args) throws Exception {
		RSAUtil rsa = new RSAUtil();
		String encode = rsa.encodeSignautre("yixin@2014");
		String decode = rsa.decodeSignautre(encode);
		System.out.println(encode + "\t" + decode);
	}
}
