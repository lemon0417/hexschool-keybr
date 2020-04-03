package com.hexschool.keybr.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexschool.keybr.model.RaceRecordDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FetchDataService {

    @Value("${google.spreadsheets.url}")
    private String url;

    @Autowired
    private RestTemplate rest;

    @Cacheable("data")
    public List<RaceRecordDto> getData() {
        ResponseEntity<String> response = rest.getForEntity(url, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode entry = null;
        try {
            JsonNode node = mapper.readTree(response.getBody());
            entry = node.path("feed").path("entry");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return transData(entry);
    }

    public List<RaceRecordDto> transData(JsonNode node) {
        List<RaceRecordDto> list = new ArrayList<>();
        RaceRecordDto dto = null;
        int row = 0;
        for (int i = 0; i < node.size(); i++) {
            JsonNode currInfo = node.get(i).path("gs$cell");
            int currRow = currInfo.path("row").asInt();
            if (currRow == 1) {
                continue;
            }

            if (row != currRow) {
                if (dto != null) {
                    list.add(dto);
                }

                row = currRow;
                dto = new RaceRecordDto();
            }

            int currCol = currInfo.path("col").asInt();
            String content = StringUtils.trimToEmpty(currInfo.path("$t").asText());
            switch (currCol) {
                case 1:
                case 5:
                    // do nothing
                    break;
                case 2:
                    dto.setNickName(content);
                    break;
                case 3:
                    dto.setFbLink(content);
                    break;
                case 4:
                    dto.setMotivate(content);
                    break;
                case 6:
                    dto.setKeybrLink(content);
                    break;
                default:
                    dto.getGrade()[currCol - 7] = Double.valueOf(content);
            }
        }
        list.add(dto);
        return list;
    }
}
