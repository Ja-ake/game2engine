package edu.catlin.springerj.g2e.web;

import java.util.ArrayList;
import java.util.List;

import edu.catlin.springerj.g2e.core.AbstractEntity;

public class WebEntity extends AbstractEntity {
	private List<AbstractEntity> web;
	private String name;
	private CheckEntity autojoin;

	public WebEntity() {
		this("");
	}

	public WebEntity(CheckEntity c) {
		this("", c);
	}

	public WebEntity(String n) {
		this("", new CheckEntity() {
			@Override
			public boolean check(AbstractEntity e) {
				return false;
			}
		});
	}

	public WebEntity(String n, CheckEntity c) {
		name = n;
		web = new ArrayList<AbstractEntity>();
		autojoin = c;
	}

	public AbstractEntity add(AbstractEntity e) {
		if (!web.contains(e)) web.add(e);
		else return null;

		return e;
	}

	public void autojoin(AbstractEntity e) {
		if (autojoin.check(e)) add(e);
	}

	public boolean check(AbstractEntity e) {
		return autojoin.check(e);
	}

	public List<AbstractEntity> getEntities() {
		return new ArrayList<AbstractEntity>(web);
	}

	public String getName() {
		return name;
	}

	@Override
	public void initialize() {

	}

	public void setName(String n) {
		name = n;
	}

	public void setRule(CheckEntity rule) {
		autojoin = rule;
	}

	@Override
	public void update() {

	}
}
