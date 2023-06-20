package com.group.shopping.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.group.shopping.domain.AwsAccessRecord;
import com.group.shopping.domain.ToBuyObject;
import com.group.shopping.repository.ShoppingRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShoppingService {

		//TODO: レコードクラスではないのでリネームする
		@Autowired
		private AwsAccessRecord awsAccessRecord;

        private final ShoppingRepository shoppingRepository;

        public List<ToBuyObject> findAll() {
                return shoppingRepository.findAll();
        }

        public void pictureSave(String pictureId) {
        	//"file"というpictureIdで到達する
        	BasicAWSCredentials awsCredencials = new BasicAWSCredentials(awsAccessRecord.getAccessKey(), awsAccessRecord.getSecretKey());

        	final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
        			.withRegion(Regions.AP_NORTHEAST_1)
        			.withCredentials(new AWSStaticCredentialsProvider(awsCredencials))
                    .build();
        	try {
        		//TODO: 画像URLはフロントから一時的なものを生成して渡し、それを元にS3にアップロード
        	    s3.putObject(awsAccessRecord.getBucketName(), pictureId, new File("/Applications/Eclipse_2020-12.app/Contents/workspace/shopping/src/main/resources/static/test.png"));
        	} catch (AmazonServiceException e) {
        	    System.err.println(e.getErrorMessage());
        	    System.exit(1);
        	}
        }

		public ToBuyObject save(String name) {
			return shoppingRepository.save(ToBuyObject.newToBuyObject(name));
		}

		public void delete(Long id) {
			shoppingRepository.findById(id).ifPresent(toBuyObject -> shoppingRepository.delete(toBuyObject));
		}
}