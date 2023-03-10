package com.driver.services;



import com.driver.models.*;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository2;
    @Autowired
    private BlogRepository blogRepository;

    public Image createAndReturn(Blog blog, String description, String dimensions){
        //create an image based on given parameters and add it to the imageList of given blog
        Image image = new Image(description,dimensions);

        image.setBlog(blog);
        List<Image> imagesList =blog.getImageList();
        if(imagesList == null){
            imagesList = new ArrayList<>();
        }
        imagesList.add(image);
        blog.setImageList(imagesList);
        blogRepository.save(blog);
        //ImageResponseDto imageResponseDto = new ImageResponseDto(image.getId(), image.getDescription(), image.getDimensions());
        return image;
    }

    public void deleteImage(Image image){
    imageRepository2.delete(image);
    }

    public Image findById(int id) {
       return imageRepository2.findById(id).get();
    }

    public int countImagesInScreen(Image image, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        //In case the image is null, return 0
        String imageDimensions = image.getDimensions();
        String [] arr1= imageDimensions.split("X");
        String [] arr2= screenDimensions.split("X");
        int xi = Integer.parseInt(arr1[0]);
        int yi = Integer.parseInt(arr1[1]);
        int xs = Integer.parseInt(arr2[0]);
        int  ys= Integer.parseInt(arr2[1]);

        int ans;
        ans = (int) ((int)Math.floor((double) xs/(double) xi)*Math.floor((double) ys/(double)yi));

      return ans;
    }

}
