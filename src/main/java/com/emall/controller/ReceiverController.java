package com.emall.controller;

import com.emall.common.R;
import com.emall.entity.Receiver;
import com.emall.service.ReceiverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/receiver")
public class ReceiverController {
        /*
    TODO
    当前controller应用场景：
    需求：
     */

    @Autowired
    private ReceiverService receiverService;

    //查看
    @GetMapping("/{receiverId}")
    public R<Receiver> getByReceiverId(@PathVariable("receiverId") Long receiverId) {
        return receiverService.getByReceiverId(receiverId);
    }

    //添加
    @PostMapping
    public R<String> insertReceiver(@RequestBody Receiver receiver) {
        return receiverService.insertReceiver(receiver);
    }

    //修改
    @PutMapping
    public R<String> updateReceiver(@RequestBody Receiver receiver) {
        return receiverService.updateReceiver(receiver);
    }

    //删除
    @DeleteMapping("/{receiverId}")
    public R<String> deleteByReceiverId(@PathVariable("receiverId") Long receiverId) {
        return receiverService.deleteByReceiverId(receiverId);
    }


}
