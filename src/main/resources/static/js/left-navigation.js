const menuToggle = document.getElementById('menuToggle');
      const closeSidebar = document.getElementById('closeSidebar');
      const sidebar = document.getElementById('sidebar');
      const sidebarOverlay = document.getElementById('sidebarOverlay');
      
      // Toggle sidebar
      menuToggle.addEventListener('click', function() {
        sidebar.classList.toggle('active');
        sidebarOverlay.classList.toggle('active');
        document.body.style.overflow = sidebar.classList.contains('active') ? 'hidden' : '';
      });
      
      // Close sidebar
      closeSidebar.addEventListener('click', function() {
        sidebar.classList.remove('active');
        sidebarOverlay.classList.remove('active');
        document.body.style.overflow = '';
      });
      
      // Close sidebar when clicking on overlay
      sidebarOverlay.addEventListener('click', function() {
        sidebar.classList.remove('active');
        sidebarOverlay.classList.remove('active');
        document.body.style.overflow = '';
      });
      
      // Handle window resize
      window.addEventListener('resize', function() {
        if (window.innerWidth > 768) {
          sidebar.classList.remove('active');
          sidebarOverlay.classList.remove('active');
          document.body.style.overflow = '';
        }
      });
      
      // Alternative dropdown toggle with click (in addition to hover)
      const profilePhoto = document.getElementById('profilePhoto');
      const profileDropdown = document.querySelector('.profile-dropdown');
      
      profilePhoto.addEventListener('click', function(e) {
        e.stopPropagation();
        profileDropdown.style.opacity = profileDropdown.style.opacity === '1' ? '0' : '1';
        profileDropdown.style.visibility = profileDropdown.style.visibility === 'visible' ? 'hidden' : 'visible';
      });
      
      // Close dropdown when clicking outside
      document.addEventListener('click', function() {
        profileDropdown.style.opacity = '0';
        profileDropdown.style.visibility = 'hidden';
      });