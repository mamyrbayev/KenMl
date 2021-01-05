package com.ereport.master.kenML.service;

import com.ereport.master.kenML.domain.Resources;
import com.ereport.master.kenML.domain.dto.OverallVolumeAndPrice;
import com.ereport.master.kenML.repository.ResourcesRepo;
import com.google.common.base.CharMatcher;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import static com.ereport.master.kenML.util.StringUtil.formatNumber;
import static com.ereport.master.kenML.util.StringUtil.formatNumberMillion;

@Service
public class ResourcesService {
    private final ResourcesRepo resourcesRepo;

    public ResourcesService(ResourcesRepo resourcesRepo) {
        this.resourcesRepo = resourcesRepo;
    }


    public List<Resources> findAllByLocalityId(Integer id){
        return resourcesRepo.findallByLocalityId(id);
    }

    public OverallVolumeAndPrice getOverallVolumeAndPrice(String codeSnb, Integer localityId){
        List<Resources> resources = resourcesRepo.findallByCodeSNBAndLocalityId(codeSnb, localityId);
        Float volume = 0f;
        Float price = 0f;

        for(Resources resources1: resources){
            volume += resources1.getResourceVolume();
            price = resources1.getPrice();
        }


        return OverallVolumeAndPrice.builder()
                .price(price * volume)
                .volume(volume)
                .build();
    }

    public OverallVolumeAndPrice getOverallVolumeAndPriceForMaterial(String codeSnb) throws ParseException {

        List<Resources> resources = resourcesRepo.findAllByCodeSNB(codeSnb);
        Float volume = 0f;
        Float price = 0f;
        String measurer = null;
        for(Resources resources1: resources){
            volume += resources1.getResourceVolume();
            price = resources1.getPrice();
            measurer = resources1.getMeasurer();
        }

        if(measurer.equals("м3")){
            measurer = "м<sup>3</sup>";
        }
        if(measurer.equals("м2")){
            measurer = "м<sup>2</sup>";
        }


        if(price >= 1){
            price = (float) Math.round(price);
        } else {
            price = formatNumber(price);
        }
        if(volume >= 1){
            volume = (float) Math.round(volume);
        } else {
            volume = formatNumber(volume);
        }
        float finalPrice = price * volume;
        String priceStr;
        String volumeStr;

        if(finalPrice >= 1){
            if (finalPrice >= 1000000){
                priceStr = formatNumberMillion(finalPrice);
            }else {
                priceStr =  String.valueOf(Math.round(finalPrice));
            }
        }else {
            priceStr = String.valueOf(formatNumber(finalPrice));
        }

        if(volume >= 1){
            if (volume >= 1000000){
                volumeStr = formatNumberMillion(volume);
            }else {
                volumeStr = String.valueOf(Math.round(volume));
            }
        }else {
            volumeStr = String.valueOf(formatNumber(volume));
        }

        return OverallVolumeAndPrice.builder()
                .price(finalPrice)
                .volume(volume)
                .priceStr(priceStr)
                .volumeStr(volumeStr)
                .measurer(measurer)
                .build();
    }

    public float formatNumber(float num) throws ParseException {
        if(num != 0f){
            DecimalFormat decimalFormat = new DecimalFormat("0.0000000000");
            String str = decimalFormat.format(num);
            if(str.length() > 1){
                while (str.endsWith("0")) {
                    str = str.substring(0, str.length() - 1);
                }
            }
            int count = CharMatcher.is('0').countIn(str);
            String secondPart = str.substring(2, count + 2);
            String finalPart = "0." + secondPart;
            NumberFormat nf = NumberFormat.getInstance(Locale.US);
            float f = nf.parse(finalPart).floatValue();
            int c = count;
            String format = "%."+c+"f";
            String number = String.format(format, f);
            number = number.replace(',', '.');
            return new Float(number);
        }else{
            return 0f;
        }
    }

    public static String fmt(double d)
    {
        if(d == (long) d)
            return String.format("%d",(long)d);
        else
            return String.format("%s",d);
    }



    public OverallVolumeAndPrice getOverallForCompany(String codeSnb, Integer companyId, Integer localityId){
        List<Resources> resources = resourcesRepo.findallByForCompany(codeSnb, companyId, localityId);
        Float volume = 0f;
        Float price = 0f;

        for(Resources resources1: resources){
            volume += resources1.getResourceVolume();
            price = resources1.getPrice();
        }
        return OverallVolumeAndPrice.builder()
                .price(price * volume)
                .volume(volume)
                .build();
    }

    public OverallVolumeAndPrice getOverallForObject(String codeSnb, Integer objectId){
        List<Resources> resources = resourcesRepo.findAllByObjectIdd(codeSnb, objectId);
        Float volume = 0f;
        Float price = 0f;

        for(Resources resources1: resources){
            volume += resources1.getResourceVolume();
            price = resources1.getPrice();
        }
        return OverallVolumeAndPrice.builder()
                .price(price * volume)
                .volume(volume)
                .build();
    }


    public OverallVolumeAndPrice getOverallForFileSection(String codeSnb, Integer fileSectionId){
        List<Resources> resources = resourcesRepo.findAllByFileSectionIdd(codeSnb, fileSectionId);
        Float volume = 0f;
        Float price = 0f;

        for(Resources resources1: resources){
            volume += resources1.getResourceVolume();
            price = resources1.getPrice();
        }
        return OverallVolumeAndPrice.builder()
                .price(price * volume)
                .volume(volume)
                .build();
    }

 }
