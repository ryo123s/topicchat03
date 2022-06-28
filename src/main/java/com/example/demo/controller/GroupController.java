package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.GroupMemberEntity;
import com.example.demo.form.GroupForm;
import com.example.demo.security.User;
import com.example.demo.service.ChatMemberService;
import com.example.demo.service.GroupMemberService;
import com.example.demo.service.GroupService;
import com.example.demo.service.ReadService;

/**
 * グループに関するコントローラー
 * @author jinjinliangjie
 *
 */
@Controller
public class GroupController {
	@Autowired
	private GroupService groupService;
	@Autowired
	private GroupMemberService groupMemberService;
	@Autowired
	private ChatMemberService chatMemberService;
	@Autowired
	private ReadService readService;
	
	/**
	 * グループ一覧画面への遷移
	 * @param user
	 * @return
	 */
	@GetMapping("/group/group")
	public String group(Model model, @AuthenticationPrincipal User user) {
		List<GroupMemberEntity> groupList = groupMemberService.getGroupListByUserId(user.getUserId());
		readService.getUnreadCountByGroupMemberEntityList(groupList);
        model.addAttribute("groupList", groupList);
		return "/group/group";
	}
	
	/**
	 * グループ追加画面への遷移
	 * @param groupForm
	 * @return
	 */
	@GetMapping("/group/addGroup")
	public String addGroup(GroupForm groupForm, Model model, @AuthenticationPrincipal User user) {
		model.addAttribute("user_id", user.getUser_id());
		return "/group/addGroup"; 
	}
	
	/**
	 * 新規グループの作成のコントローラー
	 * @param model
	 * @param groupForm
	 * @return
	 */
	@PostMapping("/addGroup")
	public String addGroup(Model model,@Valid GroupForm groupForm, BindingResult result, @AuthenticationPrincipal User user) {
		if (result.hasErrors()) {
			model.addAttribute("user_id", user.getUser_id());
			System.out.println("エラーを検出しました");
			return "/group/addGroup";
		}
		System.out.println("addGroupメソッド");
		groupService.addGroup(groupForm);
		model.addAttribute("groupList", groupMemberService.getGroupListByUserId(user.getUserId()));
		return "/group/group";
	}
	
	@GetMapping("/group/groupMember")
	public String groupMember(Model model, @ModelAttribute("groupForm")GroupForm groupForm) {
		model.addAttribute("groupMemberList", groupMemberService.getGroupMemberListByGroupId(groupForm.getGroup_id()));
		model.addAttribute("groupInfo", groupForm);
		return "/group/groupMember";
	}
	
	/**
	 * グループのメンバーに追加する
	 * @param groupMemberForm
	 * @return
	 */
	@PostMapping("/addGroupMember")
	public String addGroupMember(Model model, @ModelAttribute("groupForm")GroupForm groupForm, @AuthenticationPrincipal User user) {
		groupForm.setUser_id(user.getUserId());
		groupMemberService.addGroupMember(groupForm);
		model.addAttribute("groupMemberList", groupMemberService.getGroupMemberListByGroupId(groupForm.getGroup_id()));
		model.addAttribute("groupInfo", groupForm);
		return "/group/groupMember";
	}
	
	/**
	 * addGroupMember画面に遷移する
	 * @param model
	 * @param groupForm
	 * @return
	 */
	@GetMapping("/group/addGroupMember")
	public String addGroupMember(Model model, @ModelAttribute("groupForm")GroupForm groupForm) {
		model.addAttribute("groupForm", groupForm);
		model.addAttribute("contactList", groupMemberService.getContactListNoGroup(groupForm));
		return "/group/addGroupMember";
	}
}
