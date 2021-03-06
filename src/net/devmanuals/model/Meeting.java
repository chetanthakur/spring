package net.devmanuals.model;

// Generated Aug 31, 2013 2:18:56 PM by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Meeting generated by hbm2java
 */
@Entity
@Table(name = "meeting")
public class Meeting implements java.io.Serializable {

	private Long meetingId;
	private String subject;
	private Date meetingDate;
	private Set<Employee> employees = new HashSet<Employee>(0);

	public Meeting() {
	}

	public Meeting(String subject, Date meetingDate) {
		this.subject = subject;
		this.meetingDate = meetingDate;
	}

	public Meeting(String subject) {
		this.subject = subject;
	}

	public Meeting(String subject, Date meetingDate, Set<Employee> employees) {
		this.subject = subject;
		this.meetingDate = meetingDate;
		this.employees = employees;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "meeting_id", unique = true, nullable = false)
	public Long getMeetingId() {
		return this.meetingId;
	}

	public void setMeetingId(Long meetingId) {
		this.meetingId = meetingId;
	}

	@Column(name = "subject", nullable = false, length = 50)
	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "meeting_date", nullable = false, length = 10)
	public Date getMeetingDate() {
		return this.meetingDate;
	}

	public void setMeetingDate(Date meetingDate) {
		this.meetingDate = meetingDate;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "meetings")
	public Set<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

}
