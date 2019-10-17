/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ropandi.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@Entity
@Table(name = "table_image")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TableImage.findAll", query = "SELECT t FROM Image t")
    , @NamedQuery(name = "TableImage.findById", query = "SELECT t FROM Image t WHERE t.id = :id")
    , @NamedQuery(name = "TableImage.findByNameImage", query = "SELECT t FROM Image t WHERE t.nameImage = :nameImage")
    , @NamedQuery(name = "TableImage.findByCreatedDate", query = "SELECT t FROM Image t WHERE t.createdDate = :createdDate")
    , @NamedQuery(name = "TableImage.findByCreatedBy", query = "SELECT t FROM Image t WHERE t.createdBy = :createdBy")
    , @NamedQuery(name = "TableImage.findByUpdatedDate", query = "SELECT t FROM Image t WHERE t.updatedDate = :updatedDate")
    , @NamedQuery(name = "TableImage.findByUpdatedBy", query = "SELECT t FROM Image t WHERE t.updatedBy = :updatedBy")})
public class Image implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Column(name = "name_image")
    private String nameImage;
    @Lob
    @Column(name = "image")
    private byte[] image;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @Column(name = "updated_by")
    private String updatedBy;

    public Image() {
    }

    public Image(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameImage() {
        return nameImage;
    }

    public void setNameImage(String nameImage) {
        this.nameImage = nameImage;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Image)) {
            return false;
        }
        Image other = (Image) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication39.TableImage[ id=" + id + " ]";
    }
    
    
    
    public Image(String id, String nameImage, byte[] image, Date createdDate, String createdBy, Date updatedDate,
			String updatedBy) {
		super();
		this.id = id;
		this.nameImage = nameImage;
		this.image = image;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
	}

	public static Image newImage(String id, String nameImage, byte[] image, Date createdDate, String createdBy, Date updatedDate,
			String updatedBy) {
		 Image im = new  Image();
		 im.id = id;
			im.nameImage = nameImage;
			im.image = image;
			im.createdDate = createdDate;
			im.createdBy = createdBy;
			im.updatedDate = updatedDate;
			im.updatedBy = updatedBy;
			return im;
	}
	
	public Image build() {
		return new Image(id, nameImage, image, createdDate, createdBy, updatedDate, updatedBy);
	}
}
