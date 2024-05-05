package com.web.backend.model;

import lombok.*;

import java.util.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class MaileUser {

	private List<TargetEmail> emails=new ArrayList<TargetEmail>();


	public List<TargetEmail>  addEmails(TargetEmail  targetEmail ){
		emails.add(targetEmail);
        return emails;
    }
}
