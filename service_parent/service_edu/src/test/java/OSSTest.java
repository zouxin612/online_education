import com.aliyun.oss.OSSClient;
import org.junit.Test;

public class OSSTest {

    // Endpoint以杭州为例，其它Region请按实际情况填写。
    String endpoint = "http://oss-cn-beijing.aliyuncs.com";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
    String accessKeyId = "LTAI4GJ4EB6vyZh3aUykptZ7";
    String accessKeySecret = "LTAI4GJ4EB6vyZh3aUykptZ7\t5AsiQ0Bdux4l3Pyw3Dli5Hx5WiIOKi";
    String bucketName = "zouhan-file";

    /**
     * 测试创建Bucket
     */
    @Test
    public void testCreateBucket(){
        // 创建OSSClient实例
        OSSClient client = new OSSClient(endpoint,accessKeyId,accessKeySecret);

        //创建存储空间
        client.createBucket(bucketName);

        //关闭ossclient
        client.shutdown();
    }


    /**
     * 测试存储空间是否存在
     */
    @Test
    public void testBucketExits(){
        // 创建OSSClient实例
        OSSClient client = new OSSClient(endpoint,accessKeyId,accessKeySecret);

        // 获取客户端是否存在
        boolean bucketExist = client.doesBucketExist(bucketName);
        System.out.println(bucketExist);

        // 关闭客户端
        client.shutdown();
    }
}
