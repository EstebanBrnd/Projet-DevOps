
provider "google" {
  credentials = "${file("DevOpsProject.json")}"
  project     = var.project
  region      = var.region
  zone        = var.zone
}



resource "google_compute_instance" "vm_instance" {
  count        = 1
  name         = "${var.instance-name}-${count.index}"

  machine_type = "n1-standard-2"

  boot_disk {
    initialize_params {
      image = "debian-cloud/debian-10"
    }
  }

  network_interface {
    network       = "default"
    access_config {
    }
  }

  metadata = {
    "ssh-keys" = <<EOT
      admin:ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAACAQDYbZthZBRHCDf8voWRUCWb2LKj5EpxVDBscnqUKOjg/2JxOtggM+vq7cpGZqU49mpZ1GCce/Op98tJCqzN2lQvA0XwPJNbQz7KwlrJzntgwHU7x5BFr+T4LqwBxoINfm14OfblOQi5iWX+lU5znyxnfyERlrqEXoFI2pXl1hVWBse+Rn8oMiFYphbT3i8U673HQtqHtIi373mAfsdAJ7L8ayg3D0zzMrOiJ4XCe53gVOezY2UTBVjBlkHcvxnLI3jNfWMgDGI9Do7VNIRv5QHDeZTAX+0ze+/huTPXOgpXCT2DRtCf548dgaoG1GCDIe1c0dwHieKmu13EgkXQ8WxgQCZBpGL2mlk3VPpNi0IJ4bRj3M/IsVJAanAnHxZHwt9Dv6Qg17w7kGWxPbRjBIcUOVzt9+XpnXlezMTwFqzZ2wCPC3Z5nRnntx9ui4P8gFG5JkW+w8wngkOxhhLoQXxEzZlbxUOx+pIDTR7mFu2GDO/UGv3wEYS3xv5ZCa5MuS6IByJufRJmkI3O6owNbiwYiC83orgvzoLNKwVe8pxRxVRQTbri70OoUn+J+BSPdvPFwy5GD2XRN7mOp18Q5oOtsTWGpbobTl7fv8ZKFQb2kugZLlBNvwfwJt8+H42ccBQcbJDq/b92Li8o1HcZa2hFmCl5Grsw9FhDoMcA0KYIYw== admin
      EOT
  }

  provisioner "remote-exec" {
    inline = [
      "sudo apt update",
      "sudo apt install -y software-properties-common"
    ]
    connection {
      type        = "ssh"
      user        = "admin"
      host        = self.network_interface[0].access_config[0].nat_ip
    }
  }

  provisioner "local-exec" {
    command = "ansible-playbook -u admin -i ${self.network_interface.0.access_config.0.nat_ip}, ./ansible/main.yml"
  }
}

output "ip" {
 value = "${google_compute_instance.vm_instance[0].network_interface.0.access_config.0.nat_ip}"
}
